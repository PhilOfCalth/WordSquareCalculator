package naimuri.larkinp.controller;

import naimuri.larkinp.model.WordSquareTreeNode;

public interface WordSquareCalculator {

	public void init(int wordLength, String availableCharacters);
	public int getWordLength();
	public String getAvailableCharacters();
	public WordSquareTreeNode getTreeRoot();
}
