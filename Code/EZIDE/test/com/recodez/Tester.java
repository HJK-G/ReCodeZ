package com.recodez;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.recodez.framework.Result;
import com.recodez.framework.TerminalOutput;
import com.recodez.framework.CodeFile;
import com.recodez.handlers.ColonErrorChecker;
import com.recodez.handlers.ErrorChecker;
import com.recodez.handlers.ParenthesesErrorChecker;

public class Tester {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String filePath = "/Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/Testing/sample3.py";
		CodeFile file = new CodeFile(IOUtils.toString(new FileInputStream(new File(filePath))));

		ErrorChecker parentheses = new ParenthesesErrorChecker();
		ErrorChecker colons = new ColonErrorChecker();

		parentheses.setSuccessor(colons);

		// String error=parentheses.checkError();
		TerminalOutput.getTerminalOutput("print(1)");

	}
}
