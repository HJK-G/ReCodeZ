package com.recodez.framework;

import java.io.IOException;
import java.util.ArrayList;
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
		Process c = executeCommand(command);
		Scanner output = new Scanner(executeCommand(command).getInputStream());

		String firstLine = output.nextLine();
		// no error
		if (firstLine.equals("No compile error.")) {
			return getExecutedOutput(output);
		}

		return getCompileErrorOutput(output);
	}

	private static TerminalOutput getExecutedOutput(Scanner output) {
		ArrayList<String> outputLines = new ArrayList<>();
		String userOutput = "";
		String line = output.nextLine();
		System.out.println(line);

		outputLines.add(line);
		userOutput += line + "\n";

		while (output.hasNext()) {
			outputLines.add(line);
			userOutput += line + "\n";
			line = output.nextLine();
		}

		output.close();

		// if last line is validator, return file output
		if (line.equals(
				"0jxcuviu12@#)*!(@RJddhcv8voijrjqwd0-fisef0dcpxljeqqr83123012pscjzADOQIWJEnzcvje2AFU*(JnklJ(@#!$(%^^@&*IRKDJSZADD{||EQ{WEH~~`")) {
			return new TerminalOutput(userOutput);
		}

		// return runtime exception
		return new TerminalOutput("file", "somewhere", line);
	}

	private static TerminalOutput getCompileErrorOutput(Scanner errorOutput) {
		errorOutput.nextLine();
		errorOutput.nextLine();
		errorOutput.nextLine();
		String text = errorOutput.nextLine();
		String errorLoc = errorOutput.nextLine();
		String errorMsg = errorOutput.nextLine();

		errorOutput.close();
		return new TerminalOutput(text, errorLoc, errorMsg);
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
}
