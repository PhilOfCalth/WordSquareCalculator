package naimuri.larkinp.controller;

import org.junit.Test;
import org.junit.Before;
//A little naughty but commonly done in tests
import static org.junit.Assert.*;

public class WordSquareCalculatorTest 
{
	WordSquareCalculator wsCalculator;
	
	static final String TEST_AVAILABLE_CHARACTERS = "eeeeddoonnnsssrv";
	
	
    @Before
    public void setup()
    {
    	wsCalculator = new WordSquareCalculatorImpl();
    	wsCalculator.init(4, TEST_AVAILABLE_CHARACTERS);
    }
    
    @Test
    public void testArgumentsPassedToApi()
    {
    	assertEquals(4, wsCalculator.getWordLength());
    	assertEquals(TEST_AVAILABLE_CHARACTERS, wsCalculator.getAvailableCharacters());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArgumentsRejected()
    {
    	wsCalculator.init(5, TEST_AVAILABLE_CHARACTERS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTreeMade()
    {
    	assertNotNull(wsCalculator.getTreeRoot());
    }
}
