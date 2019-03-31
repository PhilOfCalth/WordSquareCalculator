package naimuri.larkinp.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import naimuri.larkinp.model.SearchTreeNode;
import naimuri.larkinp.util.UnreadableDictionaryException;

@Controller
public class WordSquareCalculatorImpl implements WordSquareCalculator{

	private static final String NOT_FINISHED_ANSWER = "NOT_FIN";
	
	private int wordLength; 
//	private String availableCharacters;
//	private SearchTreeNode treeRoot;
	private String[] answer;
	private StringBuilder[] remainingLetters;
	
	@Autowired
	DictionaryUtil languageDict;
	
	public String[] generateCube(int wordLength, String availableCharacters) throws UnreadableDictionaryException
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
		this.answer = new String[wordLength];
		this.answer[wordLength - 1] = NOT_FINISHED_ANSWER;
//		this.availableCharacters = availableCharacters;
		this.remainingLetters = new StringBuilder[wordLength + 1];
		this.remainingLetters[0] = new StringBuilder(availableCharacters);
		
		Set<Character> interestingCharacters = availableCharacters
												.chars()
												.mapToObj(c -> (char) c)
												.collect(Collectors.toSet());
		
		languageDict.loadDictionary(wordLength, interestingCharacters);
		
		buildAnswer();
		
		return this.answer;
	}

	private void buildAnswer()
	{
		SearchTreeNode treeRoot = new SearchTreeNode();
		buildBranch(treeRoot, 0);
	}
	
	private void buildBranch(SearchTreeNode node, int depth)
	{
		if(this.answer[wordLength - 1] == NOT_FINISHED_ANSWER)
		{
			SearchTreeNode newChild;
			String prefix = generatePrefix(depth);
			Set<String> childrenWords = languageDict.search(prefix);
			
			for(String childWord : childrenWords)
			{
				if(calculateNewUsedCharacters(childWord, depth))
				{
					newChild = new SearchTreeNode(node, childWord);
					node.getChildren().add(newChild);
					answer[depth] = childWord;
					buildBranch(newChild, depth+1);
					node.getChildren().remove(newChild);
				}
				if(this.answer[wordLength - 1] != NOT_FINISHED_ANSWER){
					break;
				}
			}
		}
	}
	
	/**
	 * @return false of there are not enough remaining characters for the new word
	 */
	private boolean calculateNewUsedCharacters(String newWord, int depth)
	{
		StringBuilder newRemainingLetters = new StringBuilder(remainingLetters[depth]);
		String usedChar;
		int charIndex;
		
		for(int i = 0; i < newWord.length(); i++)
		{
			usedChar = newWord.substring(i, i+1);
			charIndex = newRemainingLetters.indexOf(usedChar);
			if(-1 == charIndex) {
				return false;
			}
			newRemainingLetters.deleteCharAt(charIndex);
		}
		
		remainingLetters[depth+1] = newRemainingLetters;
		return true;
	}

	private String generatePrefix(int depth)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0;  i < depth; i++)
		{
			sb.append(answer[i].charAt(depth));
		}
		return sb.toString();
	}

//	public int getWordLength() {
//		return this.wordLength;
//	}
//
//	public String getAvailableCharacters() {
//		return this.availableCharacters;
//	}
//
//	public SearchTreeNode getTreeRoot() {
//		return treeRoot;
//	}
	
}
