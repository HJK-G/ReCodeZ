package io.kidspython.handlers;

import io.kidspython.TerminalOutput;

public class ParenthesesErrorChecker extends ErrorChecker {
	public ParenthesesErrorChecker(String filePath) {
		super(filePath);
	}

	public boolean handleWithThis() {
		boolean handlesWithThis = false;
		for (String line : file) {
			TerminalOutput tOutput = getTerminalOutput(line);
			if (tOutput == null) {
				continue;
			}
			String text = tOutput.getText();
			String errorMsg = tOutput.getErrorMsg();
			String errorLoc = tOutput.getErrorMsg();

			int[] parenthesesCount = new int[2];
			if (errorMsg.equals("SyntaxError: invalid syntax")
					|| errorMsg.equals("SyntaxError: unexpected EOF while parsing")) {
				boolean pastError = false;
				boolean isError = false;

				for (int i = 0; i < text.length(); i++) {
					char currChar = text.charAt(i);

					if (currChar == '(')
						parenthesesCount[pastError ? 1 : 0]++;
					else if (currChar == ')') {
						parenthesesCount[pastError ? 1 : 0]--;
						if (parenthesesCount[0] + parenthesesCount[1] < 0)
							isError = true;
					}

					if (errorLoc.charAt(i) == '^')
						pastError = true;
				}

				int sum = parenthesesCount[0] + parenthesesCount[1];
				System.out.println(parenthesesCount[0] + " " + parenthesesCount[1]);

				if (sum != 0) {
					isError = true;
				}

				if (!isError)
					continue;
				else {
					handlesWithThis = true;
				}
			}

			String message = "You have unmatched parentheses. \nYour code was: \n";
			message += text + "\n";
			String markersLeftParen = "";
			String markersRightParen = "";
			for (int i = 0; i < text.length(); i++) {
				char currChar = text.charAt(i);
				if (currChar == '(') {
					markersLeftParen += '^';
				}
				else {
					markersLeftParen += ' ';
				}

				if (currChar == ')') {
					markersRightParen += '^';
				}
				else {
					markersRightParen += ' ';
				}

			}
			message += markersLeftParen + "\n" + markersRightParen;
			System.out.println(message);
		}

		return handlesWithThis;
	}

}
