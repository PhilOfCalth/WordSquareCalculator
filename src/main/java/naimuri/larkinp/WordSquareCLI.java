package naimuri.larkinp;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import naimuri.larkinp.controller.WordSquareCalculator;
import naimuri.larkinp.util.UnreadableDictionaryException;

@Component
public class WordSquareCLI 
{	
    public static void main( String[] args ) throws UnreadableDictionaryException
    {
    	int wordLength;
    	
    	if(2 != args.length) {
    		System.out.println("Not enough arguments");
    		return;
    	}
    	try {
    		wordLength = Integer.parseInt(args[0]);
    	}
    	catch(NumberFormatException e) {
    		System.out.println("The first argument should be the word length as an integer");
    		return;
    	}
    	
    	ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("spring.xml");
    	
    	WordSquareCalculator wordSquareCalculator = (WordSquareCalculator) ctx.getBean("wordSquareCalculator");
    	printSquareToConsole(wordSquareCalculator.genorateCube(wordLength, args[1].toLowerCase()));
    }
    
	public static void printSquareToConsole(String[] square)
	{
		String word;
		
		for(int i = 0; i < square.length; i++)
		{
			word = square[i];
			System.out.println(word);
		}
	}
}