package com.recodez.framework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class TerminalAccess {

	public TerminalOutput getTerminalOutput(String code) {
		String compilePath = System.getProperty("user.dir") + "/PythonCode/CompileAndRun.py";
		String[] command = { "python", compilePath, code };
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process process = builder.start();

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

		while (scan.hasNext()) {
			String input = scan.nextLine();
			if (input.trim().equals("exit")) {
				writer.write("exit\n");
			}
			else {
				writer.write("((" + input + ") && echo --EOF--) || echo --EOF--\n");
			}
			writer.flush();

			line = reader.readLine();
			while (line != null && !line.trim().equals("--EOF--")) {
				System.out.println("Stdout: " + line);
				line = reader.readLine();
			}
			if (line == null) {
				break;
			}
		}
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
