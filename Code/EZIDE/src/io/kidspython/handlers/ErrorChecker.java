package io.kidspython.handlers;

import io.kidspython.Block;
import io.kidspython.TerminalOutput;

public abstract class ErrorChecker {
	protected ErrorChecker successor;

	public void checkError(Block currScope, TerminalOutput terminalOutput) {
		if (!handleWithThis(currScope, terminalOutput)) {
			if (successor != null) {
				successor.checkError(currScope, terminalOutput);
			}
		}
	}

	public abstract boolean handleWithThis(Block currScope, TerminalOutput terminalOutput);

	public void setSuccessor(ErrorChecker successor) {
		this.successor = successor;
	}

}
