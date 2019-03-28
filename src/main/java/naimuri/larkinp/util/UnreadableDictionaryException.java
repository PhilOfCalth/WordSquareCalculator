package naimuri.larkinp.util;

public class UnreadableDictionaryException extends Exception {

	private static final long serialVersionUID = 2663088918386181260L;

	public UnreadableDictionaryException(Exception origionalException) {
		super("Could not load dictonary", origionalException);
	}

}
