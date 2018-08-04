package io.kidspython.handlers;

import java.util.HashSet;
import java.util.Scanner;

public class ColonErrorChecker extends ErrorChecker
{
	HashSet<String> keywords;

	public ColonErrorChecker(String filePath)
	{
		super(filePath);
		keywords = new HashSet<>();
		setKeywords();
	}

	@Override
	public boolean handleWithThis()
	{
		boolean handlesWithThis = false;
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

			String message = "";
			int i = 0;
			while (text.charAt(i) == ' ' || text.charAt(i) == '\t')
			{
				message += text.charAt(i);
				i++;
			}

			for (String kword : keywords)
			{
				if (text.startsWith(kword))
				{
					if (!text.endsWith(":"))
					{
						text += ":";
						handlesWithThis = true;
					}
				}
			}
		}

		return handlesWithThis;
	}

	private void setKeywords()
	{
		keywords.add("class");
		keywords.add("try");
		keywords.add("def");
		keywords.add("if");
		keywords.add("else");
		keywords.add("elif");
		keywords.add("for");
		keywords.add("while");
		keywords.add("except");

	}

}
