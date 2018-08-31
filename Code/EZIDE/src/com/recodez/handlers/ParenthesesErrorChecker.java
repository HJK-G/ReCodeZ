package com.recodez.handlers;

import com.recodez.framework.TerminalOutput;

public class ParenthesesErrorChecker extends ErrorChecker {

	@Override
	public String handleWithThis(TerminalOutput terminalOutput) {
		String text = terminalOutput.getText().trim();

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

		if (!isError) {
			return "";
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
		message += "Your parentheses have to be matching.";

		return message;
	}

}
