package com.recodez.framework;

import com.recodez.handlers.ErrorChecker;

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

			TerminalAccess terminal = new TerminalAccess();
			TerminalOutput terminalOutput = terminal.getTerminalOutput(line, "");
			if (terminalOutput.getText() == null) {
				continue;
			}

			errorChecker.checkError(currScope, terminalOutput);

			if (!currBlock.isSingleLine()) {
				checkForErrors(errorChecker, currBlock);
			}
		}
	}
}
