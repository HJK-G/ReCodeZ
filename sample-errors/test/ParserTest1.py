import parser

try:
    parser.suite("def asdf(a) print(1)")
except SyntaxError:
    print("SOMETHING")

def asdf(a):
    print(1)