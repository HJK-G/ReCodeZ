public final class PythonParenthesesErrorRule extends ErrorRule
{
	@Override
	public boolean isThisErrorRule(CodeError error)
	{
		if (matchesSyntaxError(error))
		{
			boolean hasSeparator = true;

			for (String tokenOrSeparator : error.getSeparatedTokensAndSeparators())
			{
				System.out.println(tokenOrSeparator);
				if (PythonSpecificTerms.isToken(tokenOrSeparator))
					if (hasSeparator)
						hasSeparator = false;
					else
						return true;
				else
					hasSeparator = true;
			}
		}

		return false;
	}

	private boolean matchesSyntaxError(CodeError error)
	{
		if (error.getErrorMessage().equals("invalid syntax"))
			return true;
		if (error.getErrorMessage().equals("unexpected EOF while parsing"))
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
