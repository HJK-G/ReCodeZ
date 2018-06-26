
public class PythonParenthesesErrorRule extends PythonErrorRule
{
	@Override
	public boolean isThisErrorRule(CodeError error)
	{
		boolean canHaveAnotherToken = true;
		String code = error.getLine().getLine();

		if (matchesSyntaxError(error))
		{
			for (int i = 0; i < code.length(); i++)
			{
				
			}
		}
		
		return false;
	}

	private boolean matchesSyntaxError(CodeError error)
	{
		if (error.getErrorMessage().equals("SyntaxError: unexpected EOF while parsing"))
			return true;
		if (error.getErrorMessage().equals("SyntaxError: invalid syntax"))
			return true;
		return false;

	}

	@Override
	public void displayFixedErrorLine(CodeError error)
	{
		String message = "You have unmatched parentheses.\nYour code was: \n";
		message += error.getLine().getLine() + "\n";
		message += "Did you mean: \n";
		message += getCorrectedLine(error);

		System.out.println(message);
	}

	private String getCorrectedLine(CodeError error)
	{
		return null;
	}

}
