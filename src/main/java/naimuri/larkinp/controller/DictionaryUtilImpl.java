package naimuri.larkinp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import naimuri.larkinp.util.UnreadableDictionaryException;

@Controller
public class DictionaryUtilImpl implements DictionaryUtil{

	Resource resourceFile = new ClassPathResource("dictionary.txt");
	List<String> languageDictionary;
	
	public void loadDictionary(int wordLength, Set<Character> interestingCharacters) throws UnreadableDictionaryException
	{		
		try {
			Path dictPath = Paths.get(resourceFile.getFile().toURI());
			//May be a bit of an advantage for streams in dealing with fileIO
			List<String> words = Files.lines(dictPath)
						.filter(word -> wordLength == word.length() 
										&& DictionaryUtil.wordContainsOnlyInterestingChars(word, interestingCharacters)) 
						.collect(Collectors.toList());
			
			languageDictionary = Collections.unmodifiableList(words);
			
			
		} catch (IOException e) {
			// I would usually log this properly, but for right now, I'll just print it to the console
			e.printStackTrace();
			throw new UnreadableDictionaryException(e);
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
