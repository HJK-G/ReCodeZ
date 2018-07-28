import java.util.ArrayList;

public final class PythonParenthesesErrorRule extends ErrorRule
{
	@Override
	public boolean isThisErrorRule(CodeError error)
	{
		if (matchesSyntaxError(error))
		{
			int unpairedLeftParentheses = 0;

			String text = error.getLine().getText();
			for (int i = 0; i < text.length(); i++)
			{
				int charCodeAtIndex = text.charAt(i);
				if (charCodeAtIndex == 40)
					unpairedLeftParentheses++;
				else if (charCodeAtIndex == 41)
					if (unpairedLeftParentheses > 0)
						unpairedLeftParentheses--;
					else
						return true;
			}

			if (unpairedLeftParentheses > 0)
				return true;
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
	public void displayFixedErrorMessage(CodeError error)
	{
		String message = "You have unmatched parentheses.\nYour code was: \n";
		message += error.getLine().getText() + "\n";
		message += "Did you mean: \n";
		message += getCorrectedText(error);

		System.out.println(message);
	}

	private String getCorrectedText(CodeError error)
	{
		String correctedLine = "";
		String text = error.getLine().getText();
		boolean inParentheses = false;
		String parenthesesExpression = "";

		return correctedLine;
	}

}
