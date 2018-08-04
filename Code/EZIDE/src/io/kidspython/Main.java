package io.kidspython;

import io.kidspython.handlers.ParenthesesErrorChecker;
import io.kidspython.handlers.ColonErrorChecker;
import io.kidspython.handlers.ErrorChecker;

public class Main {
	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir") + "/Testing/sample7.py";
		ErrorChecker parentheses = new ParenthesesErrorChecker(filePath);
		ErrorChecker colons = new ColonErrorChecker(filePath);

		parentheses.setSuccessor(colons);

		parentheses.checkError();

	}
}
