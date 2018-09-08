package com.recodez.framework;

public class Result {
	private boolean executed;
	private String[] messages;
	private String output;

	public Result(String output) {
		this.output = output;
		executed = true;
	}

	public Result(String[] messages) {
		this.messages = messages;
		executed = false;
	}

	public String[] getDisplay() {
		if (isExecuted()) {
			return getOutput();
		}
		else {
			return getMessages();
		}
	}

	private boolean isExecuted() {
		return executed;
	}

	private String[] getMessages() {
		return messages;
	}

	private String[] getOutput() {
		return new String[] { "0", output };
	}
}
