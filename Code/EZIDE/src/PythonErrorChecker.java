import java.io.IOException;
import java.util.Scanner;

public class PythonErrorChecker extends ErrorChecker
{
	public PythonErrorChecker(CodeFile file)
	{
		this.file = file;
	}

	@Override
	public CodeError getError(int lineNumber)
	{
		Process results = runPythonCheckForSyntax(lineNumber);
		Scanner errorReader = new Scanner(results.getInputStream());
		CodeError error = getErrorFromReader(errorReader);

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

	private CodeError getErrorFromReader(Scanner errorReader)
	{
		String isError = errorReader.next();
		if (isError.equals("0"))
			return new CodeError("Good Statement", -1, -1, "Not Error");

		String errorMessage = errorReader.nextLine();
		String text = errorReader.nextLine();
		int lineNumber = Integer.parseInt(errorReader.next());
		int characterNumber = Integer.parseInt(errorReader.next());

		return new CodeError(text, lineNumber, characterNumber, errorMessage);
	}
}
