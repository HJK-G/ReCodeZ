package io.kidspython.handlers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import io.kidspython.TerminalOutput;

public abstract class ErrorChecker {
	protected ArrayList<String> file;
	protected ErrorChecker successor;

	public ErrorChecker(String filePath) {
		file = new ArrayList<>();
		try {
			BufferedReader f = new BufferedReader(new FileReader(filePath));
			String suite = "";
			boolean inSuite = false;
			while (f.ready()) {
				String line = f.readLine();
				if (line.trim().length() == 0) {
					continue;
				}
				inSuite = line.charAt(0) == ' ';

				if (inSuite) {
					suite += line + "\n";
				}
				else {
					if (!suite.equals("")) {
						file.add(suite);
						suite = "";
					}
					else {
						file.add(line);
					}
				}
			}
			f.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void checkError() {
		if (!handleWithThis()) {
			if (successor != null) {
				successor.checkError();
			}
		}
	}

	public abstract boolean handleWithThis();

	public void setSuccessor(ErrorChecker successor) {
		this.successor = successor;
	}

	protected static Process executeCommand(String[] command) {
		try {
			return Runtime.getRuntime().exec(command);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	protected static TerminalOutput getTerminalOutput(String line) {
		System.out.println(line);
		String[] command = { "python", System.getProperty("user.dir") + "/PythonCode/Compile.py", line };
		Scanner terminalOutput = new Scanner(executeCommand(command).getErrorStream());

		if (!terminalOutput.hasNext()) {
			terminalOutput.close();
			return null;
		}

		terminalOutput.nextLine();
		terminalOutput.nextLine();
		terminalOutput.nextLine();
		terminalOutput.nextLine();
		String text = terminalOutput.nextLine();
		String errorLoc = terminalOutput.nextLine();
		String errorMsg = terminalOutput.nextLine();
		System.out.println(text + "\n" + errorLoc + "\n" + errorMsg);

		terminalOutput.close();
		return new TerminalOutput(text, errorLoc, errorMsg);
	}
}
