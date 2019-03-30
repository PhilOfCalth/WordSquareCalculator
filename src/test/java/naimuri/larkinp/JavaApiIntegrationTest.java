package naimuri.larkinp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Test;
import naimuri.larkinp.controller.WordSquareCalculator;
import naimuri.larkinp.util.UnreadableDictionaryException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "resources/spring.xml")
public class JavaApiIntegrationTest 
{
	@Autowired
	WordSquareCalculator wsCalculator;

	@Test
    public void testAaccdeeeemmnnnoo() throws UnreadableDictionaryException
    {
    	String[] square = wsCalculator.genorateCube(4, "aaccdeeeemmnnnoo");
    	
    	validateCube(4, "aaccdeeeemmnnnoo", square);
    	WordSquareCLI.printSquareToConsole(square);
    }

	@Test
    public void testAaaeeeefhhmoonssrrrrttttw() throws UnreadableDictionaryException
    {
    	String[] square = wsCalculator.genorateCube(5, "aaaeeeefhhmoonssrrrrttttw");
    	
    	validateCube(5, "aaaeeeefhhmoonssrrrrttttw", square);
    	WordSquareCLI.printSquareToConsole(square);
    }

	@Test
    public void testMakesTestSquare() throws UnreadableDictionaryException
    {
    	String[] square = wsCalculator.genorateCube(5, "aabbeeeeeeeehmosrrrruttvv");
    	
    	validateCube(5, "aabbeeeeeeeehmosrrrruttvv", square);
    	WordSquareCLI.printSquareToConsole(square);
    }

	@Test
    public void testAaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy() throws UnreadableDictionaryException
    {
    	String[] square = wsCalculator.genorateCube(7, "aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy");
    	
    	validateCube(7, "aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy", square);
    	WordSquareCLI.printSquareToConsole(square);
    }
	
	private void validateCube(int wordLength, String characters, String[] cube)
	{
		assertEquals(wordLength, cube.length);
		
		StringBuilder remainingChars = new StringBuilder(characters);
		String word, charStr;
		int indexOfChar;
		
		for(int i = 0; i < cube.length; i++)
		{
			word = cube[i];
			assertEquals(wordLength, word.length());
			
			for(int j = 0; j < word.length(); j++)
			{
				charStr = word.substring(j, j+1);
				indexOfChar = remainingChars.indexOf(charStr);
				assertTrue(-1 != indexOfChar);
				remainingChars.deleteCharAt(remainingChars.indexOf(charStr));
			}
		}
		
		assertEquals(0, remainingChars.length());
	}
}
