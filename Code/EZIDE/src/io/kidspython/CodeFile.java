package io.kidspython;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

	private static Block buildFile() throws IOException {
		return buildScope("", 0, "");
	}

	private static Block buildScope(String beginningLine, int indentAmt, String indent) throws IOException {
		Block currScope = new Block(beginningLine, indentAmt);

		while (fileReader.ready()) {
			String currLine = fileReader.readLine();
			String currIndent = "";

			for (int i = 0; i < currLine.length() && (currLine.charAt(i) == ' ' || currLine.charAt(i) == '\t'); i++) {
				currIndent += currLine.charAt(i) == '\t' ? "    " : " ";
			}

			if (currIndent.equals(indent)) {
				currScope.addBlock(new Block(currLine, indentAmt));
			}
			else if (currIndent.length() > indent.length()) {
				currScope.addBlock(buildScope(currLine, indentAmt + 1, currIndent));
			}
			else {
				break;
			}
		}

		return currScope;
	}

	public Block getFile() {
		return file;
	}

}
