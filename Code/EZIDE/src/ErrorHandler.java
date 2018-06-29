import java.util.HashSet;

public abstract class ErrorHandler
{
	protected HashSet<ErrorRule> errorRuleChecklist;

	public abstract void handleError(CodeError error);

	public abstract void addAllErrorRules();
}
