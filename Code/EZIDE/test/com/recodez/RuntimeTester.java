package com.recodez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class RuntimeTester {
	public static void main(String[] args) throws IOException, InterruptedException {
		String code = "print(input(' asdf'))\nprint(1)";
		String[] command = { "/Users/JustinKim/anaconda3/bin/python" };
		Process p = Runtime.getRuntime().exec(command);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		PrintWriter stdOutput = new PrintWriter(new OutputStreamWriter(p.getOutputStream()));

		String s = null;
		System.out.println("Here is the standard output of the command:\n");
		int counter = 0;
		String[] lines = code.split("\n");

		for (int i = 0; i < lines.length; i++) {
			stdOutput.write(lines[i]);
			stdOutput.flush();
		}
		stdOutput.write("exit()");
		stdOutput.flush();
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			System.out.println(s);
		}
		System.out.println("ASD");

		System.exit(0);

		stdInput.close();
		stdError.close();
		stdOutput.close();
	}

}
