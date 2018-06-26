
public class CodeError
{
	private LineOfCode line;
	private int characterNumber;
	private String errorMessage;

	public CodeError(String line, int lineNumber, int characterNumber, String errorMessage)
	{
		this.line = new LineOfCode(line, lineNumber);
		this.characterNumber = characterNumber;
		this.errorMessage = errorMessage;
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
}
