package com.recodez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class RuntimeTester {
	public static void main(String[] args) throws IOException, InterruptedException {
		String code = "print(input(' asdf'))\nprint(1)";
		String[] command = { "/users/justinkim/anaconda3/bin/python", "python" };
		Process p = Runtime.getRuntime().exec(command);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		PrintWriter stdOutput = new PrintWriter(new OutputStreamWriter(p.getOutputStream()));

		int s = 0;
		System.out.println("Here is the standard output of the command:\n");
		int counter = 0;
		while ((s = stdInput.read()) != -1) {
			System.out.println(s);
			System.out.println((char) s);
			counter++;
			System.out.println(counter);

		}

		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.read()) != -1) {
			System.out.println(s);
		}
		System.out.println("ASD");

		System.exit(0);

		stdInput.close();
		stdError.close();
		stdOutput.close();
	}

}
