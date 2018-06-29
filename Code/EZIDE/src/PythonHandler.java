public final class PythonHandler extends CodeHandler
{
	public PythonHandler(String filePath)
	{
		file = new CodeFile(filePath);
		errorChecker = new PythonErrorChecker(file);
		errorHandler = new PythonErrorHandler();

		PythonSpecificTerms.setAllTerms();
	}

	@Override
	public void checkCode()
	{
		for (LineOfCode line : file.getLinesOfFile())
		{
			CodeError error = errorChecker.getError(line.getLineNumber());
			if (error.getLine().getLineNumber() != -1)
				errorHandler.handleError(error);
		}
	}

}
