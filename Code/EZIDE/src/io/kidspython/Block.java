package io.kidspython;

import java.util.LinkedList;
import java.util.Queue;

public class Block {
	private String line;
	private int indentAmt;
	private Queue<Block> blocksInScope;
	private boolean isSingleLine;

	public Block(String line, int indentAmt) {
		this.line = line.trim();
		this.indentAmt = indentAmt;
		blocksInScope = new LinkedList<>();
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

	public Block getBlock() {
		if (blocksInScope.isEmpty()) {
			return null;
		}
		return blocksInScope.remove();
	}

	public boolean isSingleLine() {
		return isSingleLine;
	}
}
