package naimuri.larkinp.model;

import java.util.HashSet;
import java.util.Set;

public class SearchTreeNode
{
	private SearchTreeNode parent;
	private String word;
	private HashSet<SearchTreeNode> children;
	
	//should this node be mutable once constructed? if not final properties and no setters
	public SearchTreeNode()
	{
		this.word = "";
		this.children = new HashSet<>();
	}
	
	public SearchTreeNode(SearchTreeNode parent, String word)
	{
		this.parent = parent;
		this.word = word;
		this.children = new HashSet<>();
	}
	
	public boolean hasChildren()
	{
		return null != children && ! children.isEmpty();
	}
	
	// I could have lombok take care of these, but I have enough dependencies for now
	public SearchTreeNode getParent() { return parent; }
	public void setParent(SearchTreeNode parent) { this.parent = parent; }
	public String getWord() { return word; }
	public void setWord(String word) { this.word = word; }
	public Set<SearchTreeNode> getChildren() { return children; }
	
}
