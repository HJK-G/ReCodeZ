import java.util.HashSet;

public final class PythonErrorHandler extends ErrorHandler
{
	public PythonErrorHandler()
	{
		errorRuleChecklist = new HashSet<>();
		addAllErrorRules();
	}

	@Override
	public void handleError(CodeError error)
	{
		for (ErrorRule errorRuleClass : errorRuleChecklist)
			if (errorRuleClass.isThisErrorRule(error))
				errorRuleClass.displayFixedErrorMessage(error);
	}

	@Override
	public void addAllErrorRules()
	{
		errorRuleChecklist.add(new PythonParenthesesErrorRule());
	}

}
