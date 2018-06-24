
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CodeFile
{
	private ArrayList<LineOfCode> linesOfFile;
	private String filePath;

	public CodeFile(String filePath)
	{
		linesOfFile = readLinesFromFile(filePath);
		this.filePath = filePath;
	}

	private static ArrayList<LineOfCode> readLinesFromFile(String filePath)
	{
		try
		{
			BufferedReader f = new BufferedReader(new FileReader(filePath));
			ArrayList<LineOfCode> lines = new ArrayList<>();
			while (f.ready())
				lines.add(new LineOfCode(f.readLine().trim(), lines.size()));
			f.close();

			return lines;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public ArrayList<LineOfCode> getLinesOfFile()
	{
		return linesOfFile;
	}
	
	public String getFilePath()
	{
		return filePath;
	}
}
