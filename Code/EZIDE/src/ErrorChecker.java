
public abstract class ErrorChecker
{
	protected CodeFile file;

	public abstract Error getError(int lineNumber);
}
