
public abstract class CodeValidityChecker
{
	CodeFile file;

	abstract void checkFile();

	abstract protected boolean hasErrors(int lineNumber);

	abstract protected void displayCorrectionMessage(int lineNumber);

	abstract protected String getFixedLine(int lineNumber);
}
