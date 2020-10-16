import parser
import sys

code = sys.argv[1]

compiled = compile(code, '<string>', 'exec')

print("No compile error.")

exec compiled
