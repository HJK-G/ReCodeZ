package com.recodez.framework;

public class TerminalOutput {
	private String text;
	private String output;
	private String errorLoc;
	private String errorMsg;

	public TerminalOutput(String text, String errorLoc, String errorMsg) {
		this.text = text;
		this.errorLoc = errorLoc;
		this.errorMsg = errorMsg;
	}

	public TerminalOutput(String output) {
		this.output = output;
	}

	public String getText() {
		return text;
	}

	public String getOutput() {
		return output;
	}

	public String getErrorLoc() {
		return errorLoc;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
