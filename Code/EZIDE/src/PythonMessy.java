import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public final class PythonMessy
{
	ArrayList<String> file;

	static HashSet<String> keywords = new HashSet<>();

	public PythonMessy(String filePath)
	{
		file = new ArrayList<>();
		try
		{
			BufferedReader f = new BufferedReader(new FileReader(filePath));

			while (f.ready())
			{
				String line = f.readLine();
				if (line.trim().length() == 0)
					continue;
				file.add(line);
			}
			f.close();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		setKeyWords();
	}

	public void checkCode()
	{
		for (String line : file)
		{
			System.out.println(line);
			String command = "python /Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/PythonCode/CheckForSyntaxError.py ";
			Scanner results = new Scanner(TextManipulator.executeCommand(command + line).getInputStream());
			if (results.nextLine().equals("Not Error"))
				continue;
			String errorMessage = results.nextLine();
			String text = results.nextLine();
			results.nextLine();
			int charNum = Integer.parseInt(results.nextLine());
			System.out.println(errorMessage + "\n" + text + "\n" + charNum);

			results.close();

			if (errorMessage.equals("invalid syntax") || errorMessage.equals("unexpected EOF while parsing"))
			{
				int unpairedLeftParentheses = 0;
				boolean error = false;

				for (int i = 0; i < text.length(); i++)
				{
					int charCodeAtIndex = text.charAt(i);
					if (charCodeAtIndex == 40)
						unpairedLeftParentheses++;
					else if (charCodeAtIndex == 41)
						if (unpairedLeftParentheses > 0)
							unpairedLeftParentheses--;
						else
							error = true;
				}

				if (unpairedLeftParentheses == 0)
					continue;
				if (!error)
					continue;
			}

			String message = "You have unmatched parentheses. \nYour code was: \n";
			message += text + "\nDid you mean: \n";

			int i = 0;
			while (text.charAt(i) == ' ' || text.charAt(i) == '\t')
				message += text.charAt(i);

			String correctedLine = "";
			String prevToken = "";
			boolean inParentheses = false;
			for (; i < text.length(); i++)
			{
				char currChar = text.charAt(i);

				if (keywords.contains(prevToken) && !inParentheses)
				{
					correctedLine += prevToken;
					prevToken = "";
				}

				if (currChar != ' ')
				{
					prevToken += currChar;
				}

				if (isFunction(prevToken))
				{
					
				}
			}

			message += correctedLine;

			System.out.println(message);
		}
	}

	public static Process executeCommand(String command)
	{
		try
		{
			return Runtime.getRuntime().exec(command);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	private static void setKeyWords()
	{
		keywords.add("False");
		keywords.add("class");
		keywords.add("finally");
		keywords.add("is");
		keywords.add("return");
		keywords.add("None");
		keywords.add("continue");
		keywords.add("for");
		keywords.add("lambda");
		keywords.add("try");
		keywords.add("True");
		keywords.add("def");
		keywords.add("from");
		keywords.add("nonlocal");
		keywords.add("while");
		keywords.add("and");
		keywords.add("del");
		keywords.add("global");
		keywords.add("not");
		keywords.add("with");
		keywords.add("as");
		keywords.add("elif");
		keywords.add("if");
		keywords.add("or");
		keywords.add("yield");
		keywords.add("assert");
		keywords.add("else");
		keywords.add("import");
		keywords.add("pass");
		keywords.add("break");
		keywords.add("except");
		keywords.add("in");
		keywords.add("raise");
	}

}
