package naimuri.larkinp.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

import naimuri.larkinp.util.UnreadableDictionaryException;

@Controller("dictionaryUtil")
public class DictionaryUtilImpl implements DictionaryUtil{

	List<String> languageDictionary;
	
	public void loadDictionary(int wordLength, Set<Character> interestingCharacters) throws UnreadableDictionaryException
	{		
				
		InputStream resourceStream = ClassLoader.getSystemClassLoader().getResourceAsStream("dictionary.txt");
			
		if(null != resourceStream)
		{
			BufferedReader resReader = new BufferedReader(new InputStreamReader(resourceStream));
			
			//May be a bit of an advantage for streams in dealing with fileIO
			List<String> words = resReader.lines()
						.filter(word -> wordLength == word.length() 
										&& DictionaryUtil.wordContainsOnlyInterestingChars(word, interestingCharacters)) 
						.collect(Collectors.toList());
			
			languageDictionary = Collections.unmodifiableList(words);
			
		} 
		else
		{
			// I would usually log this properly, but for right now, I'll just print it to the console
			System.out.println("ERROR: Could not find dictionary locally!");
			throw new UnreadableDictionaryException();
		}
	}

	public int countLoadedWords() {
		if(null != languageDictionary)
		{
			return languageDictionary.size();
		}
		return 0;
	}

	public Set<String> search(String prefix)
	{
		HashSet<String> matches = new HashSet<>();
		
		if(null != languageDictionary)
		{
			// could be done with languageDictionary.forEach()
			for(String word : languageDictionary)
			{
				if(word.startsWith(prefix)) {
					matches.add(word);
				}
			}
		}
		return matches;
	}

	
}
