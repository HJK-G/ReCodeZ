import java.util.ArrayList;

public final class CodeError
{
	private LineOfCode line;
	private int characterNumber;
	private String errorMessage;
	private ArrayList<String> separatedTokensAndSeparators;

	public CodeError(String line, int lineNumber, int characterNumber, String errorMessage,
			ArrayList<String> separatedTokensAndSeparators)
	{
		this.line = new LineOfCode(line, lineNumber);
		this.characterNumber = characterNumber;
		this.errorMessage = errorMessage;
		this.separatedTokensAndSeparators = separatedTokensAndSeparators;
	}

	public LineOfCode getLine()
	{
		return line;
	}

	public int getCharacterNumber()
	{
		return characterNumber;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public ArrayList<String> getSeparatedTokensAndSeparators()
	{
		return separatedTokensAndSeparators;
	}
}
