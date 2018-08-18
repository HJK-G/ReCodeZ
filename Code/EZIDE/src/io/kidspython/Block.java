package io.kidspython;

import java.util.ArrayList;

public class Block {
	private String line;
	private int indentAmt;
	private ArrayList<Block> blocksInScope;
	private boolean isSingleLine;

	public Block(String line, int indentAmt) {
		this.line = line.trim();
		this.indentAmt = indentAmt;
		blocksInScope = new ArrayList<>();
		isSingleLine = true;
	}

	public void addBlock(Block block) {
		blocksInScope.add(block);
		isSingleLine = false;
	}

	public String getLine() {
		return line;
	}

	public String getIndentedLine() {
		String indent = "";
		for (int i = 0; i < indentAmt; i++) {
			indent += "    ";
		}

		return indent + line;
	}

	public Block getBlock(int pos) {
		if (pos >= blocksInScope.size()) {
			return null;
		}
		return blocksInScope.get(pos);
	}

	public boolean isSingleLine() {
		return isSingleLine;
	}
}
