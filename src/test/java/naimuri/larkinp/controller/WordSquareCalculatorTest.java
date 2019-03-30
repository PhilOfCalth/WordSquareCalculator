package naimuri.larkinp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import naimuri.larkinp.model.SearchTreeNode;
import naimuri.larkinp.util.UnreadableDictionaryException;

import org.junit.Before;
//A little naughty but commonly done in tests
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "../resources/spring.xml")
public class WordSquareCalculatorTest 
{
	@Autowired
	WordSquareCalculator wsCalculator;
	
	static final String TEST_AVAILABLE_CHARACTERS = "eeeeddoonnnsssrv";
	public static final HashSet<Character> UNIQUE_CHARS_SET =  new HashSet<>(Arrays.asList('e', 'd', 'o', 'n', 's', 'r', 'v'));
	
	
    @Before
    public void setup() throws UnreadableDictionaryException
    {
    	wsCalculator.init(4, TEST_AVAILABLE_CHARACTERS);
    }
    
    @Test
    public void testArgumentsPassedToApi()
    {
    	assertEquals(4, wsCalculator.getWordLength());
    	assertEquals(TEST_AVAILABLE_CHARACTERS, wsCalculator.getAvailableCharacters());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArgumentsRejected() throws UnreadableDictionaryException
    {
    	wsCalculator.init(5, TEST_AVAILABLE_CHARACTERS);
    }

    @Test
    public void testTreeMade()
    {
    	assertNotNull(wsCalculator.getTreeRoot());
    }

//    @Test
//    public void testTreeBranchHasValidWords()
//    {
//    	//Each node of the tree after the root represents another word in the word square
//    	SearchTreeNode root = wsCalculator.getTreeRoot();
//    	
//    	assertNotNull(root.getChildren());
//    	assertFalse(root.getChildren().isEmpty());
//    	
//    	for(SearchTreeNode node: root.getChildren())
//    	{
//	    	String word = node.getWord();
//	    	assertEquals(4, word.length());
//	    	assertTrue(DictionaryUtil.wordContainsOnlyInterestingChars(word, UNIQUE_CHARS_SET));
//	    	
//	    	if(! node.getChildren().isEmpty())
//	    	{
//		    	//The list of child nodes are created by searching the dictionary with prefix created by the nodes parents
//		    	node = (SearchTreeNode) node.getChildren().toArray()[0];
//		    	String nextWord = node.getWord();
//		    	assertEquals(4, nextWord.length());
//		    	assertTrue(DictionaryUtil.wordContainsOnlyInterestingChars(word, UNIQUE_CHARS_SET));
//		    	assertEquals(word.charAt(1), nextWord.charAt(0));
//	    	}
//    	}
//    }
    
    @Test
    public void testTreeIsDestroyedWhenSolutionFound()
    {
    	//Basically the end result tree is only 1 branch, 4 deep
    	SearchTreeNode treeRoot = wsCalculator.getTreeRoot();
    	assertTrue(treeRoot.getChildren().isEmpty());
    }
}
