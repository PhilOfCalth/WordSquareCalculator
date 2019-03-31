package naimuri.larkinp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
//A little naughty but commonly done in tests
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import naimuri.larkinp.util.UnreadableDictionaryException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DictionaryUtilTest
{
	@Autowired
	DictionaryUtil languageDict;
	
    @Before
    public void setup() throws UnreadableDictionaryException //Kill the test if I can't load the dict
    {
    	languageDict.loadDictionary(4, WordSquareCalculatorTest.UNIQUE_CHARS_SET);
    }
    
    @Test   //This test might be removed if the countLoadedWords() was considered unwanted/unnecessary
    public void testDictionaryStored()
    {
    	assertTrue(0 < languageDict.countLoadedWords());
    }
    
    @Test   //Test case for acceptance 4 and 5
    public void testDictionaryNotAllStored()
    {
    	assertTrue(100 > languageDict.countLoadedWords()); //previously 170000
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
    
    @Configuration
    @ComponentScan("naimuri.larkinp")
    public static class SpringConfig {}
}
