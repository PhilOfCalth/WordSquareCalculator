package naimuri.larkinp.controller;

import naimuri.larkinp.util.UnreadableDictionaryException;
import naimuri.larkinp.util.UnsolvableWordSquareException;

public interface WordSquareCalculator {

	public String[] generateCube(int wordLength, String availableCharacters) throws UnreadableDictionaryException, UnsolvableWordSquareException;

}
