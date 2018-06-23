import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParserPython extends ParserCheckers
{
	public ParserPython(String filePath)
	{
		file = new CodeFile(filePath);
	}

	@Override
	void correctFile()
	{
		for (String line : file.getLinesOfFile())
		{
			correctLine(line);
		}
	}

	private void correctLine(String line)
	{
		if (hasErrors(line))
			displayCorrectionMessage(line);
	}

	@Override
	protected boolean hasErrors(String line)
	{
		Process resultsFromCheck = checkSyntaxForErrors(line);
		BufferedReader resultReader = getReadableResults(resultsFromCheck);
		ParserPythonError errorChecked = new ParserPythonError(resultReader);

		return errorChecked.isError;
	}

	private Process checkSyntaxForErrors(String line)
	{
		String command = "/Users/JustinKim/anaconda3/bin/python /Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/PythonCode/CheckForSyntaxError.py";
		try
		{
			line = "\"" + line + "\"";
			return Runtime.getRuntime().exec(command + " " + line);
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
	protected void displayCorrectionMessage(String line)
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
	protected String findCorrectLine(String line)
	{
		// bad algorithm
		String correctStatement = "";
		for (int i = 0; i < line.length(); i++)
		{

		}

		return "ERROR";
	}
}
