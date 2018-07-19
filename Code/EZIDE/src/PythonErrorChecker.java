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
		String command = "python /Users/JustinKim/Documents/workspace/EZIDE/upgraded-waffle/Code/EZIDE/PythonCode/CheckForSyntaxError.py ";
		Process results = PythonSpecificTerms.executePythonCode(command + file.getFilePath() + " " + lineNumber);
		Scanner errorReader = new Scanner(results.getInputStream());
		CodeError error = getErrorFromReader(errorReader);

		errorReader.close();
		return error;
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
		String previousString = "";
		boolean inToken = false;
		boolean inQuote = false;

		for (int i = 0; i < text.length(); i++)
		{
			char currentCharacter = text.charAt(i);
			boolean endQuote = false;
			if (currentCharacter == '\"' || currentCharacter == '\'')
				if (inQuote)
					endQuote = true;
				else
					inQuote = true;

			if (PythonSpecificTerms.getTokenPossibleCharacters().contains((int) currentCharacter) || inQuote)
			{
				if (!inToken && i != 0)
				{
					separated.add(previousString);
					previousString = "";
				}
				previousString += currentCharacter;
				inToken = true;
			}
			else
			{
				if (currentCharacter == '=')
					System.out.println("ASD");
				if (inToken)
				{
					separated.add(previousString);
					previousString = "";
				}
				previousString += currentCharacter;
				inToken = false;
			}
			if (endQuote)
				inQuote = false;
		}
		separated.add(previousString);

		return separated;
	}
}
