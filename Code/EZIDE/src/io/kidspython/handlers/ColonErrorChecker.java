package io.kidspython.handlers;

import java.util.HashSet;

import io.kidspython.TerminalOutput;

public class ColonErrorChecker extends ErrorChecker {
	private HashSet<String> keywords;

	public ColonErrorChecker(String filePath) {
		super(filePath);
		keywords = new HashSet<>();
		setKeywords();
	}

	@Override
	public boolean handleWithThis() {
		boolean handlesWithThis = false;
		for (String line : file) {
			TerminalOutput tOutput = getTerminalOutput(line);
			if (tOutput == null) {
				continue;
			}
			String text = tOutput.getText();

			String message = "";
			int i = 0;
			while (text.charAt(i) == ' ' || text.charAt(i) == '\t') {
				message += text.charAt(i);
				i++;
			}

			for (String kword : keywords) {
				if (text.startsWith(kword)) {
					if (!text.endsWith(":")) {
						text += ":";
						handlesWithThis = true;
					}
				}
			}

			message += text;

			System.out.println(message);
		}

		return handlesWithThis;
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
