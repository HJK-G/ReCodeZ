
public abstract class ParserCheckers
{
	CodeFile file;

	abstract void correctFile();

	abstract protected boolean hasErrors(String line);

	abstract protected void displayCorrectionMessage(String line);

	abstract protected String findCorrectLine(String line);
}
