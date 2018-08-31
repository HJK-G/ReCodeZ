package com.recodez.handlers;

import com.recodez.framework.Result;
import com.recodez.framework.TerminalOutput;

public class Executor {
	String line;

	public Executor(String file) {
		this.line = file;
	}

	public Result execute(ErrorChecker firstErrorChecker) {
		return checkForErrors(firstErrorChecker, line);
	}

	private Result checkForErrors(ErrorChecker errorChecker, String code) {
		String formattedFile = "";
		String errorMessage = "";

		TerminalOutput output = TerminalOutput.getTerminalOutput(code);
		
		

		return new Result();
	}
}
