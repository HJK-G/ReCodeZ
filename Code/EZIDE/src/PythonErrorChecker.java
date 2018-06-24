import java.io.IOException;
import java.util.Scanner;

public class PythonErrorChecker extends ErrorChecker
{
	public PythonErrorChecker(CodeFile file)
	{
		this.file = file;
	}

	@Override
	public Error getError(int lineNumber)
	{
		Process results = runPythonCheckForSyntax(lineNumber);
		Scanner errorReader = new Scanner(results.getInputStream());
		Error error = getErrorFromReader(errorReader);

		errorReader.close();
		return error;
	}

	private Process runPythonCheckForSyntax(int lineNumber)
	{
		String command = "python ./Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/PythonCode/CheckForSyntaxError.py ";
		try
		{
			return Runtime.getRuntime().exec(command + file.getFilePath() + " " + lineNumber);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private Error getErrorFromReader(Scanner errorReader)
	{
		String isError = errorReader.next();
		if (isError.equals("0"))
			return new Error("Good Statement", -1, -1, "Not Error");

		String errorMessage = errorReader.nextLine();
		String text = errorReader.nextLine();
		int lineNumber = Integer.parseInt(errorReader.next());
		int characterNumber = Integer.parseInt(errorReader.next());

		return new Error(text, lineNumber, characterNumber, errorMessage);
	}
}
