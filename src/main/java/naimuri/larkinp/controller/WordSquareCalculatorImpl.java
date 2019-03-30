package naimuri.larkinp.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import naimuri.larkinp.model.WordSquareTreeNode;
import naimuri.larkinp.util.UnreadableDictionaryException;

@Controller
public class WordSquareCalculatorImpl implements WordSquareCalculator{

	private int wordLength; 
	private String availableCharacters;
	private WordSquareTreeNode treeRoot;
	
	@Autowired
	DictionaryUtil languageDict;
	
	public void init(int wordLength, String availableCharacters) throws UnreadableDictionaryException
	{
																	//Could also be done with Math.pow
		if(null == availableCharacters || availableCharacters.length() != (wordLength * wordLength)) 
		{
			// I'd usually go for a bespoke exception here, but Illegal Argument is exactly what it is
			// and this is never used elsewhere except to display the message if I create a CLI
			throw new IllegalArgumentException(
					"There are the incorrect number of available characters given for wordlength "+wordLength);
		}
		
		this.wordLength = wordLength;
		this.availableCharacters = availableCharacters;
		
		Set<Character> interestingCharacters = availableCharacters
												.chars()
												.mapToObj(c -> (char) c)
												.collect(Collectors.toSet());
		
		languageDict.loadDictionary(wordLength, interestingCharacters);
		
		buildAnswer();
	}

	private void buildAnswer()
	{
		this.treeRoot = new WordSquareTreeNode();
		WordSquareTreeNode newChild;
		Set<String> childrenWords = languageDict.search("");
		
		for(String childWord : childrenWords)
		{
			newChild = new WordSquareTreeNode(treeRoot, childWord);
			this.treeRoot.getChildren().add(newChild);
		}
	}

	public int getWordLength() {
		return this.wordLength;
	}

	public String getAvailableCharacters() {
		return this.availableCharacters;
	}

	public WordSquareTreeNode getTreeRoot() {
		return treeRoot;
	}
	
}
