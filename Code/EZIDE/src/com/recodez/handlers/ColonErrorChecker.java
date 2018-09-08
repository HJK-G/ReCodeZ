package com.recodez.handlers;

import java.util.HashSet;

import com.recodez.framework.Block;
import com.recodez.framework.TerminalOutput;

public class ColonErrorChecker extends ErrorChecker {
	private HashSet<String> keywords;

	public ColonErrorChecker() {
		keywords = new HashSet<>();
		setKeywords();
	}

	@Override
	public boolean doesHandleWithThis(Block currScope, TerminalOutput terminalOutput) {
		String text = terminalOutput.getText().trim();

		return !text.endsWith(":");
	}

	@Override
	public String getErrorMessage(Block currScope, TerminalOutput terminalOutput) {
		String text = terminalOutput.getText().trim();

		String message = "You have a missing colon.\n";
		for (String keyword : keywords) {
			if (text.startsWith(keyword)) {
				text += ":";
			}
		}

		message += "Did you mean: \n";
		message += text;

		return message;
	}

	private void setKeywords() {
		keywords.add("class");
		keywords.add("try");
		keywords.add("def");
		keywords.add("if");
		keywords.add("else");
		keywords.add("elif");
		keywords.add("for");
		keywords.add("while");
		keywords.add("except");

	}

}
