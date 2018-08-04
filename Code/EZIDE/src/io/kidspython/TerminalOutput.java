package io.kidspython;

public class TerminalOutput {
	private String text;
	private String errorLoc;
	private String errorMsg;

	public TerminalOutput(String text, String errorLoc, String errorMsg) {
		this.text = text;
		this.errorLoc = errorLoc;
		this.errorMsg = errorMsg;
	}

	public String getText() {
		return text;
	}

	public String getErrorLoc() {
		return errorLoc;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
