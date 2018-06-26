
public abstract class ErrorRule
{
	public abstract boolean isThisErrorRule(CodeError error);

	public abstract void displayFixedErrorLine(CodeError error);

	protected static int countNumberOfSubstring(String text, String lookFor)
	{
		int count = 0;

		for (int i = 0; i < text.length(); i++)
			if ((text.charAt(i) + "").equals(lookFor))
				count++;

		return count;
	}
}
