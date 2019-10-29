package naimuri.larkinp.controller;

import naimuri.larkinp.util.UnreadableDictionaryException;
import naimuri.larkinp.util.UnsolvableWordSquareException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

//A little naughty but commonly done in tests

//Let's do this "unit test" right
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "../resources/spring.xml")
public class WordSquareCalculatorTest 
{
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
    public void testInvalidArgumentsRejected() throws UnreadableDictionaryException, UnsolvableWordSquareException
    {
    	wsCalculator.generateCube(5, TEST_AVAILABLE_CHARACTERS);
    }

    @Test
    public void testMakesTestSquare() throws UnreadableDictionaryException, UnsolvableWordSquareException
    {
    	String[] cube = wsCalculator.generateCube(4, TEST_AVAILABLE_CHARACTERS);
    	assertEquals("rose", cube[0]);
    	assertEquals("oven", cube[1]);
    	assertEquals("send", cube[2]);
    	assertEquals("ends", cube[3]);
    }
    
    //mockito is a better way of doing this!!!
    private class TesterDictionary extends DictionaryUtilImpl {
		public void loadDictionary(int wordLength, Set<Character> interestingCharacters) throws UnreadableDictionaryException {
		}
	}

}
