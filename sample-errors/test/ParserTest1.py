import parser
import sys

f=open("sample1.py","r")
s=f.readlines()
try:
    parser.suite(s)
except SyntaxError as se:
    print(se.lineno)
    print(se.offset)
    print(se.msg)