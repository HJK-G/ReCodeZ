import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class PythonMessy
{
	ArrayList<String> file;

	static HashSet<String> keywords = new HashSet<>();

	public PythonMessy(String filePath)
	{
		file = new ArrayList<>();
		try
		{
			BufferedReader f = new BufferedReader(new FileReader(filePath));
			String suite = "";
			boolean inSuite = false;
			while (f.ready())
			{
				String line = f.readLine();
				if (line.trim().length() == 0)
					continue;
				inSuite = line.charAt(0) == ' ';

				if (inSuite)
					suite += line + "\n";
				else
				{
					if (!suite.equals(""))
					{
						file.add(suite);
						suite = "";
					}
					else
						file.add(line);
				}
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
			String locationBad = results.nextLine();
			String errorMessage = results.nextLine();
			System.out.println(text + "\n" + locationBad + "\n" + errorMessage);

			results.close();
			if (text.length() > 0)
				continue;

			if (errorMessage.equals("SyntaxError: invalid syntax")
					|| errorMessage.equals("SyntaxError: unexpected EOF while parsing"))
			{
				int unpairedLeftParen = 0;
				boolean error = false;

				for (int i = 0; i < text.length(); i++)
				{
					char currChar = text.charAt(i);
					if (currChar == '(')
						unpairedLeftParen++;
					else if (currChar == ')')
						if (unpairedLeftParen > 0)
							unpairedLeftParen--;
						else
							error = true;
				}

				if (unpairedLeftParen == 0)
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
			boolean inFunctionCall = false;
			int parenCountInCall = 0;
			String functionName = "";
			for (; i < text.length(); i++)
			{
				char currChar = text.charAt(i);

				if (!inFunctionCall)
				{
					if (keywords.contains(prevToken))
					{
						correctedLine += " " + prevToken;
						prevToken = "";
					}

					if (false)
					{
						correctedLine += " " + prevToken;
						prevToken = "";
						inFunctionCall = true;
						functionName = prevToken;
					}
				}

				if (currChar != ' ')
				{
					prevToken += currChar;
				}
			}

			message += correctedLine;

			System.out.println(message);
		}
	}

	public static Process executeCommand(String[] command)
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
