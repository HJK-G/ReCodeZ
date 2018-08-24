package com.recodez.framework;

import java.io.IOException;
import java.util.Scanner;

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

	public static TerminalOutput getTerminalOutput(String code) {
		String[] command = { "python",
				"/Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/PythonCode/CompileAndRun.py",
				code };
		Process res = executeCommand(command);
		Scanner compileOutput = new Scanner(res.getInputStream());
		Scanner errorOutput = new Scanner(res.getErrorStream());

		// compile error
		if (!compileOutput.hasNext()) {
			compileOutput.close();
			return getCompileErrorOutput(errorOutput);
		}

		// exception error
		if (errorOutput.hasNext()) {
			compileOutput.close();
			return getExceptionErrorOutput(errorOutput);
		}

		// no error
		errorOutput.close();
		return getExecutedOutput(compileOutput);
	}

	private static Process executeCommand(String[] command) {
		try {
			return Runtime.getRuntime().exec(command);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static TerminalOutput getCompileErrorOutput(Scanner errorOutput) {
		errorOutput.nextLine();
		errorOutput.nextLine();
		errorOutput.nextLine();
		errorOutput.nextLine();
		String text = errorOutput.nextLine();
		String errorLoc = errorOutput.nextLine();
		String errorMsg = errorOutput.nextLine();

		errorOutput.close();
		return new TerminalOutput(text, errorLoc, errorMsg);
	}

	private static TerminalOutput getExceptionErrorOutput(Scanner output) {
		String line = "";
		while (output.hasNext()) {
			line = output.nextLine();
		}

		return new TerminalOutput("file", "somewhere", line);
	}

	private static TerminalOutput getExecutedOutput(Scanner output) {
		output.nextLine();

		String userOutput = "";

		while (output.hasNext()) {
			userOutput += output.nextLine() + "\n";
		}

		output.close();
		return new TerminalOutput(userOutput);

	}

}
