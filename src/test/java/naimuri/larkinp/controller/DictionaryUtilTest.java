package naimuri.larkinp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
//A little naughty but commonly done in tests
import static org.junit.Assert.*;

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
    	languageDict.loadDictionary();
    }
    
    @Test   //This test might be removed if the countLoadedWords() was considered unwanted/unnecessary
    public void testDictionaryStored()
    {
    	assertTrue(0 < languageDict.countLoadedWords());
    }
    
}
