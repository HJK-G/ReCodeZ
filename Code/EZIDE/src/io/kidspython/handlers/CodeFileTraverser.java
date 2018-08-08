package io.kidspython.handlers;

import java.io.IOException;
import java.util.Scanner;

import io.kidspython.Block;
import io.kidspython.CodeFile;
import io.kidspython.TerminalOutput;

public class CodeFileTraverser {
	CodeFile file;

	public CodeFileTraverser(CodeFile file) {
		this.file = file;
	}

	public void traverse(ErrorChecker firstErrorChecker) {
		this.checkForErrors(firstErrorChecker, file.getFile());
	}

	public void checkForErrors(ErrorChecker errorChecker, Block currScope) {
		Block currBlock = currScope.getBlock();
		while (currBlock != null) {
			System.out.println(currBlock.getIndentedLine());
			if (!currBlock.isSingleLine()) {
				System.out.println("asd");
				checkForErrors(errorChecker, currBlock);
			}
			String line = currBlock.getLine();
			TerminalOutput terminalOutput = getTerminalOutput(line);
			if (terminalOutput == null) {
				currBlock = currScope.getBlock();
				continue;
			}
			System.out.println(terminalOutput.getErrorMsg());
			System.out.println(terminalOutput.getText());
			System.out.println(terminalOutput.getErrorLoc());

			errorChecker.checkError(currScope, terminalOutput);

			currBlock = currScope.getBlock();
		}
	}

	private static TerminalOutput getTerminalOutput(String line) {
		String[] command = { "python", System.getProperty("user.dir") + "/PythonCode/Compile.py", line };
		Scanner terminalOutput = new Scanner(executeCommand(command).getErrorStream());

		if (!terminalOutput.hasNext()) {
			terminalOutput.close();
			return null;
		}

		terminalOutput.nextLine();
		terminalOutput.nextLine();
		terminalOutput.nextLine();
		terminalOutput.nextLine();
		String text = terminalOutput.nextLine();
		String errorLoc = terminalOutput.nextLine();
		String errorMsg = terminalOutput.nextLine();

		terminalOutput.close();
		return new TerminalOutput(text, errorLoc, errorMsg);
	}

	private static Process executeCommand(String[] command) {
		try {
			return Runtime.getRuntime().exec(command);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
