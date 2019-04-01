package naimuri.larkinp.util;

public class UnsolvableWordSquareException extends Exception {

	private static final long serialVersionUID = 2663088918386181260L;

	private final int wordLength;
	private final String availableCharacters;
	
	
	public UnsolvableWordSquareException(int wordLength, String availableCharacters)
	{
		super("The given characters ("+availableCharacters+") cannot be formed into a word square");
		
		this.availableCharacters = availableCharacters;
		this.wordLength = wordLength;
	}


	public int getWordLength() { return wordLength; }
	public String getAvailableCharacters() { return availableCharacters; }

}
