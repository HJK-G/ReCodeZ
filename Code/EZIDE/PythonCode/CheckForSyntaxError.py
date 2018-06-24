import parser
import os
import sys


f=open(sys.argv[1],"r")
fileList=f.readlines()
st=fileList[int(sys.argv[2])]

try:
    parser.suite(st)
    print("0")
except SyntaxError as se:
    print("1")
    print(se.msg)
    print(se.text)
    print(se.lineno)
    print(se.offset)
        
