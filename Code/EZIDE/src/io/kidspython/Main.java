package io.kidspython;

import io.kidspython.handlers.ParenthesesErrorChecker;
import io.kidspython.handlers.ColonErrorChecker;
import io.kidspython.handlers.ErrorChecker;

public class Main
{
	public static void main(String[] args)
	{
		String filePath = "/Users/JustinKim/documents/workspace/ezide/upgraded-waffle/code/ezide/Testing/sample1.py";
		ErrorChecker parentheses = new ParenthesesErrorChecker(filePath);
		ErrorChecker colons = new ColonErrorChecker(filePath);
		parentheses.setSuccessor(colons);
		parentheses.checkError();

	}
}
