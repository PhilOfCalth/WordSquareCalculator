package naimuri.larkinp;


import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import naimuri.larkinp.controller.DictionaryUtil;
import naimuri.larkinp.controller.WordSquareCalculator;
import naimuri.larkinp.util.UnreadableDictionaryException;

@Component
public class WordSquareCLI 
{
    public static void main( String[] args ) throws UnreadableDictionaryException
    {
//    	ApplicationContext ctx = 
//                new ClassPathXmlApplicationContext("spring.xml");
//    	WordSquareCalculator wordSquareCalculator = (WordSquareCalculator) ctx.getBean("wordSquareCalculator");
//    	wordSquareCalculator.init(4, "eeeeddoonnnsssrv");

    	//Etc
    	
    	ApplicationContext ctx = 
              new ClassPathXmlApplicationContext("spring.xml");
    	DictionaryUtil dictionaryUtil = (DictionaryUtil) ctx.getBean("dictionaryUtil");
    	dictionaryUtil.loadDictionary(4, new HashSet<>(Arrays.asList('e', 'd', 'o', 'n', 's', 'r', 'v')));
    	System.out.println(dictionaryUtil.countLoadedWords());
    }
}