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

			boolean isError = false;
			int unpairedLeftParen = 0;
			for (int i = 0; i < text.length(); i++) {
				char currChar = text.charAt(i);

				if (currChar == '(')
					unpairedLeftParen++;
				else if (currChar == ')') {
					unpairedLeftParen--;
					if (unpairedLeftParen < 0) {
						isError = true;
						break;
					}
				}
			}

			if (unpairedLeftParen != 0) {
				isError = true;
			}

			if (!isError)
				continue;
			else {
				handlesWithThis = true;
			}

			String message = "You have unmatched parentheses. \nYour code was: \n";
			message += text + "\n";
			String markersLeftParen = "";
			String markersRightParen = "";
			int numLeftParen = 0;
			int numRightParen = 0;
			for (int i = 0; i < text.length(); i++) {
				char currChar = text.charAt(i);
				if (currChar == '(') {
					markersLeftParen += '^';
					numLeftParen++;
				}
				else {
					markersLeftParen += ' ';
				}

				if (currChar == ')') {
					markersRightParen += '^';
					numRightParen++;
				}
				else {
					markersRightParen += ' ';
				}

			}
			message += markersLeftParen + "\n" + markersRightParen + "\n";
			message += "You have " + numLeftParen + " left parentheses.\n";
			message += "You have " + numRightParen + " right parentheses.\n";
			if (numLeftParen == numRightParen) {
				message += "Your parentheses have to match.";
			}
			else {
				message += "You have to have the same number of open and close parentheses and they have to match.";
			}
			System.out.println(message);
		}

		return handlesWithThis;
	}

}
