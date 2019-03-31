package naimuri.larkinp;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
    	
    	ConfigurableApplicationContext ctx = 
    			new AnnotationConfigApplicationContext("naimuri.larkinp");
                //new ClassPathXmlApplicationContext("ApplicationContext.xml");
    	
    	WordSquareCalculator wordSquareCalculator = (WordSquareCalculator) ctx.getBean("wordSquareCalculator");
    	printSquareToConsole(wordSquareCalculator.generateCube(wordLength, args[1].toLowerCase()));
    }
    
	public static void printSquareToConsole(String[] square)
	{
		String word;

		System.out.println("=======");
		for(int i = 0; i < square.length; i++)
		{
			word = square[i];
			System.out.println(word);
		}
	}

}