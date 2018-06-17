import parser
import os

os.chdir("/Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/sample-errors/e9-pair-parentheses")
f=open("sample2.py","r")
fileList=f.readlines()

s=""
for st in fileList:
    s+=st
    
try:
    parser.suite(s)
except SyntaxError as se:
    print(se.lineno)
    print(se.offset)
    print(se.msg)
    print(se.text)
    print(se.with_traceback)