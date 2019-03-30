package naimuri.larkinp.controller;

import naimuri.larkinp.model.SearchTreeNode;
import naimuri.larkinp.util.UnreadableDictionaryException;

public interface WordSquareCalculator {

	public String[] genorateCube(int wordLength, String availableCharacters) throws UnreadableDictionaryException;
//	public int getWordLength();
//	public String getAvailableCharacters();
//	public SearchTreeNode getTreeRoot();
}
