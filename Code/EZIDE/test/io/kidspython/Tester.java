package io.kidspython;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import io.kidspython.handlers.CodeFileTraverser;
import io.kidspython.handlers.ColonErrorChecker;
import io.kidspython.handlers.ErrorChecker;
import io.kidspython.handlers.ParenthesesErrorChecker;

public class Tester {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String filePath = System.getProperty("user.dir") + "/Testing/sample7.py";
		CodeFile file = new CodeFile(filePath);
		CodeFileTraverser fileTraverser = new CodeFileTraverser(file);
		ErrorChecker parentheses = new ParenthesesErrorChecker();
		ErrorChecker colons = new ColonErrorChecker();

		parentheses.setSuccessor(colons);

		fileTraverser.traverse(parentheses);

		String message = ErrorChecker.getMessages().peek();

		String correct = IOUtils
				.toString(new FileInputStream(new File(System.getProperty("user.dir") + "/Testing/sample7Output.txt")));
		System.out.println(message);
	}
}
