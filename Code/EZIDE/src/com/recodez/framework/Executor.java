package com.recodez.framework;

import com.recodez.handlers.ColonErrorChecker;
import com.recodez.handlers.ErrorChecker;
import com.recodez.handlers.ParenthesesErrorChecker;
import com.recodez.handlers.VariableNameErrorChecker;

public class Executor {
	CodeFile file;
	String line;

	public Executor(CodeFile file) {
		this.file = file;
		this.line = file.getFileString();
	}

	public Result execute() {
		return checkForErrors(line);
	}

	private Result checkForErrors(String code) {
		TerminalAccess terminal = new TerminalAccess();
		TerminalOutput overallOutput = terminal.getTerminalOutput(code);

		// executed
		if (overallOutput.getOutput() != null) {
			return new Result(overallOutput.getOutput());
		}

		CodeFileTraverser fileTraverser = new CodeFileTraverser(file);
		ErrorChecker parentheses = new ParenthesesErrorChecker();
		ErrorChecker colons = new ColonErrorChecker();
		ErrorChecker varName = new VariableNameErrorChecker();

		parentheses.setSuccessor(colons);
		colons.setSuccessor(varName);

		fileTraverser.traverse(parentheses);

		int size = ErrorChecker.getMessages().size();
		String[] messages = new String[size];

		for (int i = 0; i < size; i++) {
			messages[i] = ErrorChecker.getMessages().remove();
		}

		return new Result(messages);
	}
}
