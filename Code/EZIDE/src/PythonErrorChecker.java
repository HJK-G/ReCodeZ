import java.io.IOException;
import java.util.ArrayList;
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
		Process results = runPythonCheckForSyntax(lineNumber);
		Scanner errorReader = new Scanner(results.getInputStream());
		CodeError error = getErrorFromReader(errorReader);

		errorReader.close();
		return error;
	}

	private Process runPythonCheckForSyntax(int lineNumber)
	{
		String command = "python /Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/PythonCode/CheckForSyntaxError.py ";
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
			return new CodeError("Good Statement", -1, -1, "Not Error", new ArrayList<>());

		errorReader.nextLine();
		String errorMessage = errorReader.nextLine();
		String text = errorReader.nextLine();
		int lineNumber = Integer.parseInt(errorReader.next());
		int characterNumber = Integer.parseInt(errorReader.next());
		ArrayList<String> separatedIntoTokensAndSeparators = separateIntoTokensAndSeparators(text);

		return new CodeError(text, lineNumber, characterNumber, errorMessage, separatedIntoTokensAndSeparators);
	}

	private ArrayList<String> separateIntoTokensAndSeparators(String text)
	{
		ArrayList<String> separated = new ArrayList<>();
		String prevString = "";
		boolean inToken = false;
		for (int i = 0; i < text.length(); i++)
		{
			if (PythonSpecificTerms.getTokenPossibleCharacters().contains((int) text.charAt(i)))
			{
				
			}
			else
			{

			}
		}

		separated.add("print");
		separated.add(" (");
		separated.add("3");
		separated.add(" + ");
		separated.add("9");
		separated.add(" * (");
		separated.add("4");
		separated.add(" + ");
		separated.add("5");
		separated.add(")");

		return separated;
	}
}
