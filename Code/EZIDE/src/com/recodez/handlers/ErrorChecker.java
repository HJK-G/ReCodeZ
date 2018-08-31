package com.recodez.handlers;

import com.recodez.framework.TerminalOutput;

public abstract class ErrorChecker {
	protected ErrorChecker successor;

	public String checkError(TerminalOutput terminalOutput) {
		String resThisCheck = handleWithThis(terminalOutput);

		if (resThisCheck.equals("")) {
			if (successor != null) {
				return successor.checkError(terminalOutput);
			}
		}

		return resThisCheck;
	}

	public abstract String handleWithThis(TerminalOutput terminalOutput);

	public void setSuccessor(ErrorChecker successor) {
		this.successor = successor;
	}

}
