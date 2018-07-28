import parser
import os
import sys

st=sys.argv[1]

try:
    parser.suite(st)
    print("Not Error")
except SyntaxError as se:
    print("Error")
    print(se.msg)
    print(se.text)
    print(se.offset)
        
