import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public final class PythonMessy
{
	ArrayList<String> file;

	public PythonMessy(String filePath)
	{
		file = new ArrayList<>();
		try
		{
			BufferedReader f = new BufferedReader(new FileReader(filePath));

			while (f.ready())
				file.add(f.readLine());
			f.close();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void checkCode()
	{
		for (String file : file)
		{
			String command = "python /Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/PythonCode/CheckForSyntaxError.py ";
			Scanner results = new Scanner(TextManipulator.executeCommand(command + file).getInputStream());
			if (results.nextLine().equals("Not Error"))
				continue;
			String errorMessage = results.nextLine();
			String text = results.nextLine();
			int charNum = Integer.parseInt(results.nextLine());

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

			Stack<String> line = new Stack<>();
			String message = "You have unmatched parentheses. \nYour code was: \n";
			message += text + "\nDid you mean: \n";
			for (int i = 0; i < text.length(); i++)
			{
				char currentChar = text.charAt(i);

			}
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

}
