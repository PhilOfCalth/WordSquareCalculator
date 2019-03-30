package naimuri.larkinp.controller;

import org.junit.Test;
import naimuri.larkinp.util.UnreadableDictionaryException;

import org.junit.Before;
//A little naughty but commonly done in tests
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Let's do this "unit test" right
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "../resources/spring.xml")
public class WordSquareCalculatorTest 
{
//	@Autowired
	WordSquareCalculatorImpl wsCalculator;
	
	static final String TEST_AVAILABLE_CHARACTERS = "eeeeddoonnnsssrv";
	public static final HashSet<Character> UNIQUE_CHARS_SET =  new HashSet<>(Arrays.asList('e', 'd', 'o', 'n', 's', 'r', 'v'));
	
	@Before
	public void setup() throws UnreadableDictionaryException
	{
		//mockito a better way of doing this
		DictionaryUtilImpl dict = new TesterDictionary();
		ArrayList<String> words = new ArrayList<>();
		words.add("ends");
		words.add("oven");
		words.add("nevr");
		words.add("rose");
		words.add("send");
		
		dict.languageDictionary = words;
	  	wsCalculator = new WordSquareCalculatorImpl();
	  	wsCalculator.languageDict = dict;
	}
	

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArgumentsRejected() throws UnreadableDictionaryException
    {
    	wsCalculator.genorateCube(5, TEST_AVAILABLE_CHARACTERS);
    }

    @Test
    public void testMakesTestSquare() throws UnreadableDictionaryException
    {
    	String[] cube = wsCalculator.genorateCube(4, TEST_AVAILABLE_CHARACTERS);
    	assertEquals("rose", cube[0]);
    	assertEquals("oven", cube[1]);
    	assertEquals("send", cube[2]);
    	assertEquals("ends", cube[3]);
    }
    
    //mockito a better way of doing this!!!
    private class TesterDictionary extends DictionaryUtilImpl{
    	public void loadDictionary(int wordLength, Set<Character> interestingCharacters) throws UnreadableDictionaryException{}
    }
	
//    @Before
//    public void setup() throws UnreadableDictionaryException
//    {
//    	wsCalculator.init(4, TEST_AVAILABLE_CHARACTERS);
//    }
//    
//    @Test
//    public void testArgumentsPassedToApi()
//    {
//    	assertEquals(4, wsCalculator.getWordLength());
//    	assertEquals(TEST_AVAILABLE_CHARACTERS, wsCalculator.getAvailableCharacters());
//    }
//
//    @Test
//    public void testTreeMade()
//    {
//    	assertNotNull(wsCalculator.getTreeRoot());
//    }
//
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
//    
//    @Test
//    public void testTreeIsDestroyedWhenSolutionFound()
//    {
//    	//Basically the end result tree is only 1 branch, 4 deep
//    	SearchTreeNode treeRoot = wsCalculator.getTreeRoot();
//    	assertTrue(treeRoot.getChildren().isEmpty());
//    } 

}
