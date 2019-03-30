package naimuri.larkinp.controller;

import java.util.Set;

import naimuri.larkinp.util.UnreadableDictionaryException;

public interface DictionaryUtil {

	void loadDictionary(int wordLength, Set<Character> interestingCharacters) throws UnreadableDictionaryException;
	int countLoadedWords();
	Set<String> search(String prefix); //TODO swap these out for CharacterArrays
	
	static boolean wordContainsOnlyInterestingChars(String word, Set<Character> interestingCharacters)
	{
		//Old school but I'm fairly sure this is the fastest way to do things here
		for(int i = 0; i < word.length(); i++){
			if( ! interestingCharacters.contains(word.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
