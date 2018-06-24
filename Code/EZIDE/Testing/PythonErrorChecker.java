import java.io.BufferedReader;
import java.util.Scanner;

public class PythonErrorChecker
{
	boolean isError;
	String message;
	String text;
	int lineNumber;
	int charNumber;

	public PythonErrorChecker(BufferedReader results)
	{
		Scanner s = new Scanner(results);
		String lastLine = s.nextLine();
		if (lastLine.equals("0"))
			isError = false;
		else
		{
			isError = true;
			message = lastLine;
			text = s.nextLine();
			lineNumber = Integer.parseInt(s.nextLine());
			charNumber = Integer.parseInt(s.nextLine());

		}

	}
	
	public void hasError
	{
		Process resultsFromCheck = checkSyntaxForErrors(line);
		BufferedReader resultReader = getReadableResults(resultsFromCheck);
		PythonSyntaxError errorChecked = new PythonSyntaxError(resultReader);

		System.out.println(errorChecked.isError);
		return errorChecked.isError;
	}
}
