

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class CodeFile
{
	private String[] linesOfFile;

	public CodeFile(String filePath)
	{
		linesOfFile = readLinesFromFile(filePath);
	}

	private static String[] readLinesFromFile(String filePath)
	{
		return readFile(filePath).split("\n");
	}

	private static String readFile(String filePath)
	{
		try
		{
			BufferedReader f = new BufferedReader(new FileReader(filePath));
			File file = new File(filePath);
			Charset encoding = Charset.forName("UTF-8");
			byte[] encoded = Files.readAllBytes(file.toPath());
			return new String(encoded, encoding);
		}
		catch (IOException e)
		{
			return "";
		}
	}

	public String[] getLinesOfFile()
	{
		return linesOfFile;
	}
}
