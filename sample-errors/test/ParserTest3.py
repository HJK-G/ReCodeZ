import parser
import os


filePath="/sample-errors/e9-pair-parentheses/"
fileName="sample4.py"

def getListOfLinesFromFile():
    file=getFileFromFilePath()
    return file.readlines()
    
def getFileFromFilePath():
    changeDirectoryToFilePath()
    return open(fileName)
    
def changeDirectoryToFilePath():
    currentPath=os.getcwd()
    newPath=currentPath[0:currentPath.index("/upgraded-waffle")]+"/upgraded-waffle"+filePath
    os.chdir(newPath)


    
def main():
    linesInFile=getListOfLinesFromFile()
    
    
    #loop through all lines in file to find 1st error
    for i in range(len(linesInFile)):
        st=linesInFile[i]
        try:
            parser.suite(st)
        except SyntaxError as se:
            first=se
            print(st)
            break
        
main()