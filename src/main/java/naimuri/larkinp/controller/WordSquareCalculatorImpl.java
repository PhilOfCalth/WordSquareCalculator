package naimuri.larkinp.controller;

import naimuri.larkinp.util.UnreadableDictionaryException;
import naimuri.larkinp.util.UnsolvableWordSquareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Set;
import java.util.stream.Collectors;

@Controller("wordSquareCalculator")
public class WordSquareCalculatorImpl implements WordSquareCalculator{

	private static final String NOT_FINISHED_ANSWER = "NOT_FIN";
	
	private int wordLength;
	private String[] answer;
	private StringBuilder[] remainingLetters;
	
	@Autowired
	DictionaryUtil languageDict;
	
	public String[] generateCube(int wordLength, String availableCharacters) throws UnreadableDictionaryException, UnsolvableWordSquareException
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
		
		if(this.answer[wordLength - 1] != NOT_FINISHED_ANSWER) {
			return this.answer;
		}
		throw new UnsolvableWordSquareException(wordLength, availableCharacters);
	}

	private void buildAnswer()
	{
		buildBranch(0);
	}
	
	private void buildBranch(int depth)
	{
		if(this.answer[wordLength - 1] == NOT_FINISHED_ANSWER)
		{
			String prefix = genoratePrefix(depth);
			Set<String> childrenWords = languageDict.search(prefix);
			String curWord = depth > 0 ? answer[depth - 1] : null;
			
			for(String childWord : childrenWords)
			{
				if(calculateNewUsedCharacters(childWord, depth))
				{
					answer[depth] = childWord;
					buildBranch(depth+1);
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

	private String genoratePrefix(int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append(answer[i].charAt(depth));
		}
		return sb.toString();
	}
	
}
