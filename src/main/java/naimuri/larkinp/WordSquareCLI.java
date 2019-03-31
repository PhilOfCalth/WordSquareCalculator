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

    	// consistent bracket placement
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
    	printSquareToConsole(wordSquareCalculator.generateCube(wordLength, args[1].toLowerCase()));
    }

    // can be private
	public static void printSquareToConsole(String[] square)
	{

//		can do in a java 8 way, which people like!
//		Arrays.stream(square).forEach(System.out::println);
		String word;
		
		for(int i = 0; i < square.length; i++)
		{
			word = square[i];
			System.out.println(word);
		}
	}
}