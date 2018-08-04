package io.kidspython.handlers;

import java.util.Scanner;

public class ParenthesesErrorChecker extends ErrorChecker
{
	public ParenthesesErrorChecker(String filePath)
	{
		super(filePath);
	}

	public boolean handleWithThis()
	{
		boolean handlesWithThis = true;
		for (String line : file)
		{
			System.out.println(line);
			String[] command =
				{ "python", System.getProperty("user.dir") + "/PythonCode/Compile.py", line };
			Scanner results = new Scanner(executeCommand(command).getErrorStream());
			if (!results.hasNext())
				continue;
			results.nextLine();
			results.nextLine();
			results.nextLine();
			results.nextLine();
			String text = results.nextLine();
			String errorLoc = results.nextLine();
			String errorMessage = results.nextLine();
			System.out.println(text + "\n" + errorLoc + "\n" + errorMessage);

			results.close();

			int[] parenthesesCount = new int[2];
			if (errorMessage.equals("SyntaxError: invalid syntax")
					|| errorMessage.equals("SyntaxError: unexpected EOF while parsing"))
			{
				boolean pastError = false;
				boolean isError = false;

				for (int i = 0; i < text.length(); i++)
				{
					char currChar = text.charAt(i);

					if (currChar == '(')
						parenthesesCount[pastError ? 1 : 0]++;
					else if (currChar == ')')
					{
						parenthesesCount[pastError ? 1 : 0]--;
						if (parenthesesCount[0] + parenthesesCount[1] < 0)
							isError = true;
					}

					if (errorLoc.charAt(i) == '^')
						pastError = true;
				}

				int sum = parenthesesCount[0] + parenthesesCount[1];
				System.out.println(parenthesesCount[0] + " " + parenthesesCount[1]);

				if (sum != 0)
				{
					isError = true;
				}

				if (!isError)
					continue;
				else
				{
					handlesWithThis = true;
				}
			}

			String message = "You have unmatched parentheses. \nYour code was: \n";
			message += text + "\n";
			String markersLeftParen = "";
			String markersRightParen = "";
			for (int i = 0; i < text.length(); i++)
			{
				char currChar = text.charAt(i);
				if (currChar == '(')
				{
					markersLeftParen += '^';
				}
				
				else if (currChar == ')')
				{
					markersRightParen += '^';
				}
				else
				{
					markersLeftParen += ' ';
					markersRightParen += ' ';
				}

			}
			message += markersLeftParen + "\n" + markersRightParen;
			System.out.println(message);
		}

		return handlesWithThis;
	}

}
