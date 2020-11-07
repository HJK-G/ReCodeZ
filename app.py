from flask import Flask, render_template
from flask_socketio import SocketIO
import pty
import os
import subprocess
import select
import termios
import struct
import fcntl
# import pymongo
import json

path = './resources'
app = Flask(__name__, template_folder=path, static_folder=path, static_url_path="")
app.config["SECRET_KEY"] = "adsjkflajsoiejlqndvm,ojasdlfanfe"
app.config["cmd"] = ["bash"]
app.config["fd"] = None
app.config["child_pid"] = None
app.config["running"] = False
# app.config["client"] = pymongo.MongoClient("mongodb://0.0.0.0:27017")
# app.config["database"] = app.config["client"]["ReCodeZ"]["errors"]

socketio = SocketIO(app)

def escapeCode(code):
    escode = ""
    last = 0
    for i in range(len(code)):
        if code[i] == '"':
            escode += code[last:i]+"'"
            last = i+1

        if code[i] == '\n':
            escode += code[last:i]+"\\n"
            last = i+1
    escode += code[last:]
    return escode

def modifyOutput(output):
    if not app.config["running"]:
        return ""

    # catch syntax errors
    if output[:7] == "printf " and output[-52:] == "[ec2-user@ip-172-26-5-101 recodez]$ python tmp1. py\n":
        return ""

    # stop running
    if output[-36:] == "[ec2-user@ip-172-26-5-101 recodez]$ ":
        app.config["running"] = False
        return output[:-36]

    return output


def set_winsize(fd, row, col, xpix=0, ypix=0):
    winsize = struct.pack("HHHH", row, col, xpix, ypix)
    fcntl.ioctl(fd, termios.TIOCSWINSZ, winsize)


def read_and_forward_pty_output():
    max_read_bytes = 1024*20
    while True:
        socketio.sleep(0.01)
        if app.config["fd"]:
            timeout_sec = 0
            (data_ready, _, _) = select.select([app.config["fd"]], [], [], timeout_sec)
            if data_ready:
                output = os.read(app.config["fd"], max_read_bytes).decode()
                output = modifyOutput(output)
                socketio.emit("pty-output", {"output": output}, namespace = "/pty")


@app.route("/")
def index():
    return render_template("index.htm")

@socketio.on("run-code", namespace = "/pty")
def run_code(data):
    if app.config["fd"]:
        code = data["input"].encode()

        app.config["running"] = True

        code = escapeCode(code)
        writefilecmd = "printf \"" + code + "\" > tmp1.py\n"
        print "writing code to file"
        # socketio.emit("pty-output", {"running file": writefilecmd}, namespace = "/pty")
        os.write(app.config["fd"], writefilecmd)

        runfilecmd = "python tmp1.py\n"
        print "running file"
        # socketio.emit("pty-output", {"running file": runfilecmd}, namespace = "/pty")
        os.write(app.config["fd"], runfilecmd)

@socketio.on("pty-input", namespace = "/pty")
def pty_input(data):
    if app.config["fd"] and app.config["running"]:
        ptyin = data["input"].encode()
        print ptyin
        os.write(app.config["fd"], ptyin)


@socketio.on("resize", namespace = "/pty")
def resize(data):
    if app.config["fd"]:
        set_winsize(app.config["fd"], data["rows"], data["cols"])


@socketio.on("connect", namespace = "/pty")
def connect():
    if app.config["child_pid"]:
        return

    (child_pid, fd) = pty.fork()

    if child_pid == 0:
        subprocess.call(app.config["cmd"])
    else:
        app.config["fd"] = fd
        app.config["child_pid"] = child_pid

        print "child pid is {}".format(child_pid)
        print "starting bash task to read and forward pty output to client"

        socketio.start_background_task(target = read_and_forward_pty_output)
        print("task started")

def main():
    port = 5000
    print "creating host on port {}".format(port)
    socketio.run(app, debug = False, port = port, host = "0.0.0.0")

if __name__ == "__main__":
    main()
