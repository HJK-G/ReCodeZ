import java.util.Scanner;

public final class PythonErrorChecker extends ErrorChecker
{
	public PythonErrorChecker(CodeFile file)
	{
		this.file = file;
	}

	@Override
	public CodeError getError(int lineNumber)
	{
		String command = "python /Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/PythonCode/CheckForSyntaxError.py ";
		Process results = TextManipulator.executeCommand(command + file.getFilePath() + " " + lineNumber);
		Scanner errorReader = new Scanner(results.getInputStream());
		CodeError error = getErrorFromReader(errorReader);

		errorReader.close();
		return error;
	}

	private CodeError getErrorFromReader(Scanner errorReader)
	{
		String isError = errorReader.next();
		if (isError.equals("0"))
			return new CodeError("Good Statement", -1, -1, "Not Error");

		errorReader.nextLine();
		String errorMessage = errorReader.nextLine();
		String text = errorReader.nextLine();
		int lineNumber = Integer.parseInt(errorReader.next());
		int characterNumber = Integer.parseInt(errorReader.next());

		return new CodeError(text, lineNumber, characterNumber, errorMessage);
	}
}
