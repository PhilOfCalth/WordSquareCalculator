package naimuri.larkinp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import naimuri.larkinp.model.WordSquareTreeNode;

@Controller
public class WordSquareCalculatorImpl implements WordSquareCalculator{

	private int wordLength; 
	private String availableCharacters;
	private WordSquareTreeNode treeRoot;
	
	@Autowired
	DictionaryUtil languageDict;
	
	public void init(int wordLength, String availableCharacters)
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
		this.treeRoot = new WordSquareTreeNode();
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
