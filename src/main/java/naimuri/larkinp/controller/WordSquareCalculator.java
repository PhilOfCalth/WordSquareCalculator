package naimuri.larkinp.controller;

public interface WordSquareCalculator {

	public void init(int wordLength, String availableCharacters);
	public int getWordLength();
	public String getAvailableCharacters();
	//public Node getTreeRoot();
}
