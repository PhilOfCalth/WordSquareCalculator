package naimuri.larkinp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
//A little naughty but commonly done in tests
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import naimuri.larkinp.util.UnreadableDictionaryException;


/**
 * Unit test for simple App.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class DictionaryUtilTest
{
	DictionaryUtil languageDict;
	
    @Before
    public void setup() throws UnreadableDictionaryException //Kill the test if I can't load the dict
    {
    	languageDict = new DictionaryUtilImpl();
    	languageDict.loadDictionary(4, new HashSet<>(Arrays.asList('e', 'd', 'o', 'n', 's', 'r', 'v')));
    }
    
    @Test   //This test might be removed if the countLoadedWords() was considered unwanted/unnecessary
    public void testDictionaryStored()
    {
    	assertTrue(0 < languageDict.countLoadedWords());
    }
    
    @Test   //Test case for acceptance 4 and 5
    public void testDictionaryNotAllStored()
    {
    	assertTrue(3903 > languageDict.countLoadedWords()); //previously 170000
    }

    @Test
    public void testDictionarySearchByPrefixAndRemainingChars()
    {
    	/** So working from the example from the specs:
    	 * Input: 4 eeeeddoonnnsssrv
    	 * Step 1:
    	 *  Found: nothing
    	 *  prefix: 
    	 * 
    	 */
    	Set<String> matchingWords = languageDict.search("");
    	assertTrue(matchingWords.contains("rose"));
    	
    	/** Step 2:
    	 *  Found:
    	 *   rose
    	 *   o***
    	 *   s***
    	 *   e***
    	 *   
    	 * Prefix: o
    	 *
    	 */
    	matchingWords = languageDict.search("o");
    	assertTrue(matchingWords.contains("oven"));
    	
    	/** Step 3:
    	 *  Found:
    	 *   rose
    	 *   oven
    	 *   se**
    	 *   en**
    	 *   
    	 * Prefix: se
    	 *
    	 */
    	matchingWords = languageDict.search("se");
    	assertTrue(matchingWords.contains("send"));
    	
    	/** Step 4:
    	 *  Found:
    	 *   rose
    	 *   oven
    	 *   send
    	 *   end*
    	 *   
    	 * Next prefix: end
    	 * Remaining characters: s
    	 *
    	 */
    	matchingWords = languageDict.search("end");
    	assertTrue(matchingWords.contains("ends"));
    	
    	matchingWords = languageDict.search("ends");
    	assertTrue(matchingWords.contains("ends"));
    	assertEquals(1, matchingWords.size());

    }
    
}
