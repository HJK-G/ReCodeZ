package com.recodez.framework;

import java.io.IOException;
import java.util.Scanner;

public class TerminalAccess {

	public TerminalOutput getTerminalOutput(String code) {
		String compilePath = System.getProperty("user.dir") + "/PythonCode/CompileAndRun.py";
		String[] command = { "python", compilePath, code };
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

	private Process executeCommand(String[] command) {
		try {
			return Runtime.getRuntime().exec(command);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private TerminalOutput getCompileErrorOutput(Scanner errorOutput) {
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

	private TerminalOutput getExceptionErrorOutput(Scanner output) {
		String line = "";
		while (output.hasNext()) {
			line = output.nextLine();
		}

		return new TerminalOutput("file", "somewhere", line);
	}

	private TerminalOutput getExecutedOutput(Scanner output) {
		output.nextLine();

		String userOutput = "";

		while (output.hasNext()) {
			userOutput += output.nextLine() + "\n";
		}

		output.close();
		return new TerminalOutput(userOutput);

	}

}
