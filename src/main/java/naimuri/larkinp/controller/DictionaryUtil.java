package naimuri.larkinp.controller;

import java.util.Set;

import naimuri.larkinp.util.UnreadableDictionaryException;

public interface DictionaryUtil {

	void loadDictionary(int wordLength, Set<Character> interestingCharacters) throws UnreadableDictionaryException;
	int countLoadedWords();

}
