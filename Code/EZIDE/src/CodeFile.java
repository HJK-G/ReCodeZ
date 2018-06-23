
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CodeFile
{
	private String[] linesOfFile;

	public CodeFile(String filePath)
	{
		linesOfFile = readLinesFromFile(filePath);
	}

	private static String[] readLinesFromFile(String filePath)
	{
		try
		{
			BufferedReader f = new BufferedReader(new FileReader(filePath));
			ArrayList<String> lines = new ArrayList<String>();
			while (f.ready())
				lines.add(f.readLine());
			f.close();

			String[] linesFromFile = new String[lines.size()];
			for (int i = 0; i < lines.size(); i++)
			{
				System.out.println(lines.get(i));
				linesFromFile[i] = lines.get(i);
			}

			return linesFromFile;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return new String[] {};
	}

	public String[] getLinesOfFile()
	{
		return linesOfFile;
	}
}
