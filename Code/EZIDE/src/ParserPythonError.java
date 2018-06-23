import java.io.BufferedReader;
import java.io.IOException;

public class ParserPythonError
{
	boolean isError;
	String message;
	String text;
	int lineNumber;
	int charNumber;

	public ParserPythonError(BufferedReader results)
	{
		try
		{
			if (results.readLine().equals("0"))
				isError = false;
			else
			{
				isError = true;
				message = results.readLine();
				text = results.readLine();
				lineNumber = Integer.parseInt(results.readLine());
				charNumber = Integer.parseInt(results.readLine());
			}
		}
		catch (IOException e)
		{
			isError = false;
		}
	}
}
