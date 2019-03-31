package naimuri.larkinp.model;

import java.util.HashSet;
import java.util.Set;

public class SearchTreeNode
{
	private final SearchTreeNode parent;
	private final String word;
	private final HashSet<SearchTreeNode> children;
	

	public SearchTreeNode()
	{
		this.parent = null;
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
	public String getWord() { return word; }
	public Set<SearchTreeNode> getChildren() { return children; }
	
}
