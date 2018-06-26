
public abstract class ErrorChecker
{
	protected CodeFile file;

	public abstract CodeError getError(int lineNumber);
}
