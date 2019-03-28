package naimuri.larkinp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import naimuri.larkinp.util.UnreadableDictionaryException;

@Controller
public class DictionaryUtilImpl implements DictionaryUtil{

	Resource resourceFile = new ClassPathResource("naimuri/larkinp/resources/dictionary.txt");
	List<String> languageDictionary;
	
	public void loadDictionary(int wordLength, Set<Character> interestingCharacters) throws UnreadableDictionaryException
	{		
		try {
			Path dictPath = Paths.get(resourceFile.getFile().toURI());
			//May be a bit of an advantage for streams in dealing with fileIO
			List<String> words = Files.lines(dictPath)
						.filter(word -> wordLength == word.length() 
										&& wordContainsOnlyInterestingChars(word, interestingCharacters)) 
										//I could be more fancy than this and get into regex and etc, but this is probibly the fastest way
						.collect(Collectors.toList());
			
			languageDictionary = Collections.unmodifiableList(words);
			
			
		} catch (IOException e) {
			// I would usually log this properly, but for right now, I'll just print it to the console
			e.printStackTrace();
			throw new UnreadableDictionaryException(e);
		}
	}

	private boolean wordContainsOnlyInterestingChars(String word, Set<Character> interestingCharacters) {
		
		//Old school but I'm fairly sure this is the fastest way to do things here
		for(int i = 0; i < word.length(); i++){
			if( ! interestingCharacters.contains(word.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public int countLoadedWords() {
		if(null != languageDictionary)
		{
			return languageDictionary.size();
		}
		return 0;
	}

	
}
