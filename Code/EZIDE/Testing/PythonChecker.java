import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonChecker extends CodeValidityChecker
{
	public PythonChecker(String filePath)
	{
		file = new CodeFile(filePath);
	}

	@Override
	void checkFile()
	{
		for (int i = 0; i < file.getLinesOfFile().size(); i++)
		{
			checkLine(i);
		}
	}

	private void checkLine(int lineNumber)
	{
		if (hasErrors(lineNumber))
			displayCorrectionMessage(lineNumber);
	}

	@Override
	protected boolean hasErrors(int lineNumber)
	{
		
	}

	private Process checkSyntaxForErrors(String line)
	{
		String command = "python /Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/PythonCode/CheckForSyntaxError1.py ";
		try
		{
			int lineno = 0;
			line = line.replace("\n", "");
			return Runtime.getRuntime().exec(command
					+ "/Users/JustinKim/documents/workspace/ezide/upgraded-waffle/code/ezide/testing/sample2.py "
					+ lineno);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private BufferedReader getReadableResults(Process resultsFromCheck)
	{
		return new BufferedReader(new InputStreamReader(resultsFromCheck.getInputStream()));
	}

	@Override
	protected void displayCorrectionMessage(int lineNumber)
	{
		String message = getDefaultCorrectionMessage(line);
		message += findCorrectLine(line);

		System.out.println(message);
	}

	private String getDefaultCorrectionMessage(String line)
	{
		String message = "You have unmatched parentheses.\nYour code was: \n";
		message += line + "\n";
		message += "Did you mean: \n";

		return message;
	}

	@Override
	protected String getCorrectLine(int lineNumber)
	{
		// bad algorithm
		String correctStatement = "";
		for (int i = 0; i < line.length(); i++)
		{

		}

		return "ERROR";
	}
}
