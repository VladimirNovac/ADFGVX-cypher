package ie.gmit.dip;

public class ContainerArray {

	private int numberRows;
	private int remainder;
	private int additionalRows;
	private int rows;
	private char[][] textArray;

	/**
	 * Creates an array to store either the plain or encrypted text.
	 * @param text
	 * @param codeWord
	 * @param encrypt
	 */
	public ContainerArray(String text, String codeWord, boolean encrypt) {
		this.numberRows = text.length() / codeWord.length();
		this.remainder = text.length() % codeWord.length();
		if (encrypt) {
			if (remainder > 0) {
				this.additionalRows = 2;
			} else {
				this.additionalRows = 1;
			}
			this.rows = numberRows * 2 + additionalRows;
		}
		if(!encrypt) {
			if (remainder > 0) {
				this.additionalRows = 1;
			} else {
				this.additionalRows = 0;
			}
			this.rows = numberRows + additionalRows;
		}
		
		this.textArray = new char[rows][codeWord.length()];
	}

	public int getNumberRows() {
		return numberRows;
	}

	public int getRemainder() {
		return remainder;
	}

	public int getAdditionalRows() {
		return additionalRows;
	}

	public int getRows() {
		return rows;
	}

	public char[][] getTextArray() {
		return textArray;
	}

}
