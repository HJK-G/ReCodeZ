package io.kidspython;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

public class CodeFile {
	private Block file;

	private static BufferedReader fileReader;

	public CodeFile(String filePath) {
		try {
			fileReader = new BufferedReader(new FileReader(filePath));
			file = buildFile();
			fileReader.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CodeFile(String file, boolean isFile) {
		try {
			fileReader = new BufferedReader(new InputStreamReader(IOUtils.toInputStream(file)));
			this.file = buildFile();
			fileReader.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Block buildFile() throws IOException {
		return buildScope("", 0, "");
	}

	private static Block buildScope(String beginningLine, int indentAmt, String prevIndent) throws IOException {
		Block currScope = new Block(beginningLine, indentAmt);

		while (fileReader.ready()) {
			fileReader.mark(100000);
			String currLine = fileReader.readLine();
			String currIndent = getIndent(currLine);
			if (currIndent.length() <= prevIndent.length() && indentAmt > 0) {
				fileReader.reset();
				return currScope;
			}
			else {
				currScope.addBlock(buildScope(currLine, indentAmt + 1, currIndent));
			}
		}

		return currScope;
	}

	private static String getIndent(String line) {
		String indent = "";
		for (int i = 0; i < line.length() && (line.charAt(i) == ' ' || line.charAt(i) == '\t'); i++) {
			indent += line.charAt(i) == '\t' ? "    " : " ";
		}

		return indent;
	}

	public Block getFile() {
		return file;
	}

}
