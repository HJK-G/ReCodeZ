package io.kidspython.handlers;

import java.util.LinkedList;
import java.util.Queue;

import io.kidspython.Block;
import io.kidspython.TerminalOutput;

public abstract class ErrorChecker {
	protected ErrorChecker successor;
	protected static Queue<String> messages = new LinkedList<>();

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

	public static Queue<String> getMessages() {
		return messages;
	}

}
