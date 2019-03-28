package naimuri.larkinp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import naimuri.larkinp.util.UnreadableDictionaryException;

@Controller
public class DictionaryUtilImpl implements DictionaryUtil{

	Resource resourceFile = new ClassPathResource("naimuri/larkinp/resources/dictionary.txt");
	List<String> languageDictionary;
	
	public void loadDictionary() throws UnreadableDictionaryException
	{
		CopyOnWriteArrayList<String> words = new CopyOnWriteArrayList<>();
		
		try {
			Path dictPath = Paths.get(resourceFile.getFile().toURI());
			//May be a bit of an advantage for streams in dealing with fileIO
			Files.lines(dictPath)
				 .forEach(line -> words.add(line));
			
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

	
}
