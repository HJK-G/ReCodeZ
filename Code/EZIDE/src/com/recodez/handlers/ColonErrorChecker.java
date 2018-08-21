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
	public boolean handleWithThis(Block currScope, TerminalOutput terminalOutput) {
		String text = terminalOutput.getText().trim();

		String message = "You have a missing colon.\n";
		boolean isError = false;
		for (String keyword : keywords) {
			if (text.startsWith(keyword)) {
				if (!text.endsWith(":")) {
					text += ":";
					isError = true;
				}
			}
		}

		if (!isError)
			return false;

		message += "Did you mean: \n";
		message += text;
		System.out.println(message);
		messages.add(message);

		return true;
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
