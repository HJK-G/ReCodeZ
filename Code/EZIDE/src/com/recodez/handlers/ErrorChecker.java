package com.recodez.handlers;

import java.util.LinkedList;
import java.util.Queue;

import com.recodez.framework.Block;
import com.recodez.framework.TerminalOutput;

public abstract class ErrorChecker {
	protected ErrorChecker successor;
	protected static Queue<String> messages = new LinkedList<>();

	public void checkError(Block currScope, TerminalOutput terminalOutput) {
		if (!doesHandleWithThis(currScope, terminalOutput)) {
			if (successor != null) {
				successor.checkError(currScope, terminalOutput);
			}
		}
		else {
			messages.add(getErrorMessage(currScope, terminalOutput));
		}
	}

	public abstract boolean doesHandleWithThis(Block currScope, TerminalOutput terminalOutput);

	public abstract String getErrorMessage(Block currScope, TerminalOutput terminalOutput);

	public void setSuccessor(ErrorChecker successor) {
		this.successor = successor;
	}

	public static Queue<String> getMessages() {
		return messages;
	}

}
