import parser, sys

line=sys.argv[1]

try:
    parser.suite(line)
    print("0")
except SyntaxError as se:
    print(se.lineno)
    print(se.offset)
    print(se.msg)
    print(se.text)