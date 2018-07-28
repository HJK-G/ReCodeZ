import java.io.IOException;
import java.util.HashSet;

public final class TextManipulator
{
	private static HashSet<String> tokenSeparators = new HashSet<>();

	private static HashSet<Integer> tokenPossibleCharacters = new HashSet<>();
	
	public TextManipulator(String text)
	{
		setAllTerms();
	}
	
	public static void setAllTerms()
	{
		setTokenPossibleCharacters();
	}

	private static void setTokenPossibleCharacters()
	{
		for (int i = 65; i <= 90; i++)
			tokenPossibleCharacters.add(i);
		for (int i = 97; i <= 122; i++)
			tokenPossibleCharacters.add(i);
		for (int i = 48; i <= 57; i++)
			tokenPossibleCharacters.add(i);
		tokenPossibleCharacters.add(95);
		tokenPossibleCharacters.add(46);
	}

	public static Process executeCommand(String command)
	{
		try
		{
			return Runtime.getRuntime().exec(command);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}
	
	public static boolean isToken(String test)
	{
		boolean isToken = tokenPossibleCharacters.contains((int) test.charAt(0))
				|| test.substring(0, 1).equals("\"");

		return isToken;
	}

	public static boolean isSeparator(String test)
	{
		return !isToken(test);
	}

	public static HashSet<String> getTokenSeparators()
	{
		return tokenSeparators;
	}

	public static HashSet<Integer> getTokenPossibleCharacters()
	{
		return tokenPossibleCharacters;
	}

}
