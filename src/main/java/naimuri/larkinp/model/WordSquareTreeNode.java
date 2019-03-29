package naimuri.larkinp.model;

import java.util.HashSet;

public class WordSquareTreeNode
{
	private WordSquareTreeNode parent;
	private String word;
	private HashSet<WordSquareTreeNode> children;
	

	public WordSquareTreeNode() {
		super();
	}
	
	public WordSquareTreeNode(WordSquareTreeNode parent, String word) {
		super();
		this.parent = parent;
		this.word = word;
	}

	// I could have lombok take care of these, but I have enough dependencies for now
	public WordSquareTreeNode getParent() { return parent; }
	public void setParent(WordSquareTreeNode parent) { this.parent = parent; }
	public String getWord() { return word; }
	public void setWord(String word) { this.word = word; }
	public HashSet<WordSquareTreeNode> getChildren() { return children; }
	public void setChildren(HashSet<WordSquareTreeNode> children) { this.children = children; }
	
}
