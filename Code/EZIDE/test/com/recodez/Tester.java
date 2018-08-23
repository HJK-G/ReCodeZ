package com.recodez;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.recodez.framework.CodeFile;
import com.recodez.handlers.CodeFileTraverser;
import com.recodez.handlers.ColonErrorChecker;
import com.recodez.handlers.ErrorChecker;
import com.recodez.handlers.ParenthesesErrorChecker;

public class Tester {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String filePath = "/Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/Testing/sample1.py";
		CodeFile file = new CodeFile(filePath);
		CodeFileTraverser fileTraverser = new CodeFileTraverser(file);
		ErrorChecker parentheses = new ParenthesesErrorChecker();
		ErrorChecker colons = new ColonErrorChecker();

		parentheses.setSuccessor(colons);

		fileTraverser.traverse(parentheses);

		String message = ErrorChecker.getMessages().peek();

		System.out.println(message);
	}
}
