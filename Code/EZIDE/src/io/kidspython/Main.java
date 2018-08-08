package io.kidspython;

import io.kidspython.handlers.CodeFileTraverser;
import io.kidspython.handlers.ColonErrorChecker;
import io.kidspython.handlers.ErrorChecker;
import io.kidspython.handlers.ParenthesesErrorChecker;

public class Main {
	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir") + "/Testing/sample7.py";
		CodeFile file = new CodeFile(filePath);
		CodeFileTraverser fileTraverser = new CodeFileTraverser(file);
		ErrorChecker parentheses = new ParenthesesErrorChecker();
		ErrorChecker colons = new ColonErrorChecker();

		parentheses.setSuccessor(colons);

		fileTraverser.traverse(parentheses);

	}
}
