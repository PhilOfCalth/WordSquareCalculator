package naimuri.larkinp.controller;

import naimuri.larkinp.model.WordSquareTreeNode;
import naimuri.larkinp.util.UnreadableDictionaryException;

public interface WordSquareCalculator {

	public void init(int wordLength, String availableCharacters) throws UnreadableDictionaryException;
	public int getWordLength();
	public String getAvailableCharacters();
	public WordSquareTreeNode getTreeRoot();
}
