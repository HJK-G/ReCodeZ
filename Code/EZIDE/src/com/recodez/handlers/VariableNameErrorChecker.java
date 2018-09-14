package com.recodez.handlers;

import com.recodez.framework.Block;
import com.recodez.framework.TerminalOutput;

public class VariableNameErrorChecker extends ErrorChecker {

	@Override
	public boolean doesHandleWithThis(Block currScope, TerminalOutput terminalOutput) {
		return terminalOutput.getErrorMsg().startsWith("NameError:");
	}

	@Override
	public String getErrorMessage(Block currScope, TerminalOutput terminalOutput) {
		String exceptionError = terminalOutput.getErrorMsg();
		String variable = exceptionError.substring(17, exceptionError.indexOf('\'', 18));
		String message = "You have an undefined variable.\n";
		message += "The variable was: ";
		message += variable;
		message += "\nYou cannot use a variable without first defining it.";

		return message;
	}

}
