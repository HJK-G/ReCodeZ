package com.recodez.handlers;

import java.io.IOException;
import java.util.Scanner;

import com.recodez.framework.Block;
import com.recodez.framework.CodeFile;
import com.recodez.framework.TerminalOutput;

public class CodeFileTraverser {
	CodeFile file;

	public CodeFileTraverser(CodeFile file) {
		this.file = file;
	}

	public void traverse(ErrorChecker firstErrorChecker) {
		this.checkForErrors(firstErrorChecker, file.getFile());
	}

	public void checkForErrors(ErrorChecker errorChecker, Block currScope) {
		Block currBlock = currScope.getBlock(0);
		for (int pos = 0; currBlock != null; pos++, currBlock = currScope.getBlock(pos)) {
			String line = currBlock.getLine();
			TerminalOutput terminalOutput = getTerminalOutput(line);
			if (terminalOutput == null) {
				continue;
			}

			errorChecker.checkError(currScope, terminalOutput);

			if (!currBlock.isSingleLine()) {
				checkForErrors(errorChecker, currBlock);
			}
		}
	}

	private static TerminalOutput getTerminalOutput(String code) {
		String[] command = { "python", "/Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/PythonCode/Compile.py", code };
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
