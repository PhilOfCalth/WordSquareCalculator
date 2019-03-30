package naimuri.larkinp.model;

import java.util.HashSet;
import java.util.Set;

public class WordSquareTreeNode
{
	private WordSquareTreeNode parent;
	private String word;
	private HashSet<WordSquareTreeNode> children;
	

	public WordSquareTreeNode()
	{
		super();
		children = new HashSet<>();
	}
	
	public WordSquareTreeNode(WordSquareTreeNode parent, String word)
	{
		this();
		this.parent = parent;
		this.word = word;
	}
	
	public boolean hasChildren()
	{
		return null != children && ! children.isEmpty();
	}
	
	// I could have lombok take care of these, but I have enough dependencies for now
	public WordSquareTreeNode getParent() { return parent; }
	public void setParent(WordSquareTreeNode parent) { this.parent = parent; }
	public String getWord() { return word; }
	public void setWord(String word) { this.word = word; }
	public Set<WordSquareTreeNode> getChildren() { return children; }
	
}
