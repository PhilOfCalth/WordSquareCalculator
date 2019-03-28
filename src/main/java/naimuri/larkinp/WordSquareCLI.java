package naimuri.larkinp;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import naimuri.larkinp.controller.DictionaryUtil;
import naimuri.larkinp.controller.WordSquareCalculator;
import naimuri.larkinp.util.UnreadableDictionaryException;

public class WordSquareCLI 
{
    public static void main( String[] args ) throws UnreadableDictionaryException
    {
//    	ApplicationContext ctx = 
//                new ClassPathXmlApplicationContext("spring.xml");
//    	WordSquareCalculator wordSquareCalculator = (WordSquareCalculator) ctx.getBean("wordSquareCalculator");

    	//Etc
    	
    	ApplicationContext ctx = 
              new ClassPathXmlApplicationContext("spring.xml");
    	DictionaryUtil dictionaryUtil = (DictionaryUtil) ctx.getBean("dictionaryUtil");
    	dictionaryUtil.loadDictionary(4, new HashSet<>(Arrays.asList('e', 'd', 'o', 'n', 's', 'r', 'v')));
    	System.out.println(dictionaryUtil.countLoadedWords());
    }
}