package naimuri.larkinp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import naimuri.larkinp.model.WordSquareTreeNode;
import naimuri.larkinp.util.UnreadableDictionaryException;

import org.junit.Before;
//A little naughty but commonly done in tests
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

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

    @Test
    public void testTreeBranchHasValidWord()
    {
    	WordSquareTreeNode root = wsCalculator.getTreeRoot();
    	
    	assertNotNull(root.getChildren());
    	assertFalse(root.getChildren().isEmpty());
    	
    	// Painful to do but I believe HashSet is the best datastructure for the function outside the test
    	// and I'm fairly sure I'm going to have to get rid of this test in a later refactor stage
    	WordSquareTreeNode node = (WordSquareTreeNode) root.getChildren().toArray()[0];
    	String word = node.getWord();
    	assertEquals(4, word.length());
    	assertTrue(DictionaryUtil.wordContainsOnlyInterestingChars(word, UNIQUE_CHARS_SET));
    	
    }
}
