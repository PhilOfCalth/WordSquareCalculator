package naimuri.larkinp.controller;

import naimuri.larkinp.util.UnreadableDictionaryException;

public interface DictionaryUtil {

	void loadDictionary() throws UnreadableDictionaryException;
	int countLoadedWords();

}
