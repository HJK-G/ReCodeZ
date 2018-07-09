public final class LineOfCode
{
	private String text;
	private int lineNumber;

	public LineOfCode(String text, int lineNumber)
	{
		this.text = text;
		this.lineNumber = lineNumber;
	}

	public String getText()
	{
		return text;
	}

	public int getLineNumber()
	{
		return lineNumber;
	}
}
