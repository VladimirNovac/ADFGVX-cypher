package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class ADFGVX {

	// modified two dimensional array to encapsulate all characters
	private char[][] ADFGVX = { 
			{ '~', 'A', 'D', 'F', 'G', 'V', 'X', 'Y', 'Z' },
			{ 'A', ' ', 'H', '0', 'Q', 'G', '6', ':', '@' }, 
			{ 'D', '4', 'M', 'E', 'A', '1', 'Y', '"', '#' },
			{ 'F', 'L', '2', 'N', 'O', 'F', 'D', '(', '$' }, 
			{ 'G', 'X', 'K', 'R', '3', 'C', 'V', ')', '^' },
			{ 'V', 'S', '5', 'Z', 'W', '7', 'B', '`', '{' }, 
			{ 'X', 'J', '9', 'U', 'T', 'I', 'P', ';', '}' },
			{ 'Y', '8', '.', '-', '+', '/', '?', '!', '>' }, 
			{ 'Z', ',', '_', '&', '*', '|', '=', '%', '<' },
			{ 'M', '\r', '\n' } };

	public ADFGVX() {

	}

	/**
	 * Main encrypt method. It encrypts the text and populates and array with it.
	 * Then, it creates a final transposed String using the indices of the codeword.
	 * 
	 * @param text
	 * @param codeWord
	 * @return transposedString
	 */
	public String encryptText(String text, String codeWord) {
		int[] codeNumbers = arrangeCodeWord(codeWord);
		String encryptedString = StringFromEncryption(text);
		ContainerArray containerArray = new ContainerArray(text, codeWord, true);
		char[][] textArray = containerArray.getTextArray();

		// If any spaces are left at the end of the array, it fills them with " "
		// (space)
		int count = 0;
		for (int row = 0; row < textArray.length; row++) {
			for (int col = 0; col < containerArray.getTextArray()[row].length; col++) {
				if (count == encryptedString.length()) {
					textArray[row][col] = 'A';
				} else {
					textArray[row][col] = encryptedString.charAt(count);
					count++;
				}
			}
		}

		String transposedString = stringFromEncryptedArray(codeNumbers, containerArray.getRows(), textArray);

		return transposedString;
	}

	/**
	 * Main decrypt method. It takes the encrypted text and populates and array with
	 * it. Then it loops through the array, sorts the columns by the codeword
	 * indices and exports the columns to a String. The String is decoded and
	 * returned.
	 * 
	 * @param text
	 * @param codeWord
	 * @return decodedText
	 */
	public String decryptText(String text, String codeWord) {
		int[] codeNumbers = arrangeCodeWord(codeWord);
		ContainerArray containerArray = new ContainerArray(text, codeWord, false);
		char[][] textArray = containerArray.getTextArray();
		int rows = containerArray.getRows();

		String[] getRows = getRows(text, rows);

		for (int row = 0; row < codeNumbers.length; row++) {
			for (int col = 0; col < codeNumbers.length; col++) {
				if (codeNumbers[row] == col) {
					for (int z = 0; z < rows; z++) {
						int codeNumber = codeNumbers[col];
						textArray[z][col] = getRows[codeNumber].charAt(z);
					}
				}
			}
		}

		StringBuilder sortedEncodedText = new StringBuilder();
		for (int i = 0; i < textArray.length; i++) {
			for (int j = 0; j < textArray[i].length; j++) {
				sortedEncodedText.append(textArray[i][j]);
			}
		}

		String decodedText = stringFromDecryption(sortedEncodedText.toString());
		return decodedText;
	}

	/**
	 * Calculates the number of rows needed to hold the encrypted text.
	 * 
	 * @param text
	 * @param rows
	 * @return stringArray
	 */
	private String[] getRows(String text, int rows) {
		String[] stringArray = new String[text.length() / rows + (text.length() % rows == 1 ? 1 : 0)];
		int counter = 0;
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = text.substring(counter * rows, counter * rows + rows);
			counter++;
		}
		return stringArray;
	}

	/**
	 * Generates a String from the encrypted matrix using the codeword indices as
	 * append order.
	 * 
	 * @param codeNumbers
	 * @param rows
	 * @param textArray
	 * @return transposedString
	 */
	private String stringFromEncryptedArray(int[] codeNumbers, int rows, char[][] textArray) {
		StringBuilder transposedString = new StringBuilder();
		for (int x = 0; x < codeNumbers.length; x++) {
			for (int y = 0; y < codeNumbers.length; y++) {
				if (x == codeNumbers[y]) {
					for (int a = 0; a < rows; a++) {
						transposedString.append(textArray[a][y]);
					}
				}
			}
		}
		return transposedString.toString();
	}

	/**
	 * It takes the plain text and encodes it to a String using
	 * getEncodedCharacters() method.
	 * 
	 * @param text
	 * @return encryptedString
	 */
	private String StringFromEncryption(String text) {
		StringBuilder encryptedString = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			encryptedString.append(getEncodedCharacters(Character.toUpperCase(text.charAt(i))));
		}
		return encryptedString.toString();
	}

	/**
	 * Gets a single letter input. Searches for the letter in the ADFGVX matrix and
	 * returns the corresponding two encoding letters.
	 * 
	 * @param textArray
	 * @return encodedLetters
	 */
	private String getEncodedCharacters(char textArray) {
		StringBuilder encodedLetters = new StringBuilder();
		for (int row = 1; row < ADFGVX.length; row++) {
			for (int col = 1; col < ADFGVX[row].length; col++) {
				if (textArray == ADFGVX[row][col]) {
					encodedLetters.append(ADFGVX[row][0]);
					encodedLetters.append(ADFGVX[col][0]);
				}
			}
		}
		return encodedLetters.toString();
	}

	/**
	 * It takes the encrypted text and populated a char array. Then it decodes the
	 * characters to a String using getDecodedCharacters() method.
	 * 
	 * @param encText
	 * @return decodedText
	 */
	private String stringFromDecryption(String encText) {
		char[][] charArray = encodedTextToCharArray(encText);
		StringBuilder decodedText = new StringBuilder();
		for (int i = 0; i < charArray.length; i++) {
			decodedText.append(getDecodedCharacters(charArray[i][0], charArray[i][1]));
		}
		return decodedText.toString();
	}

	/**
	 * Converts the encrypted text to char array. The char array always has 2
	 * columns for the two encoded characters that make up a symbol.
	 * 
	 * @param encText
	 * @return charArray
	 */
	private char[][] encodedTextToCharArray(String encText) {
		char[][] charArray = new char[encText.length() / 2][2];
		int count = 0;
		for (int i = 0; i < charArray.length; i++) {
			for (int j = 0; j < 2; j++) {
				charArray[i][j] = encText.charAt(count);
				count++;
			}
		}
		return charArray;
	}

	/**
	 * Receives two encoded characters, decodes them and returns a string.
	 * 
	 * @param firstLetter
	 * @param secondLetter
	 * @return decodedText
	 */
	private String getDecodedCharacters(char firstLetter, char secondLetter) {
		StringBuilder decodedText = new StringBuilder();
		for (int row = 1; row < ADFGVX.length; row++) {
			if (firstLetter == ADFGVX[row][0]) {
				for (int col = 1; col < ADFGVX[row].length; col++) {
					if (secondLetter == ADFGVX[0][col]) {
						decodedText.append(ADFGVX[row][col]);
					}
				}
			}
		}
		return decodedText.toString();
	}

	/**
	 * It takes the codeword and gets the indices of each letter that will be used
	 * to transpose the encrypted or decrypted text. A simple bubble sort algorithm
	 * is used.
	 * 
	 * @param String codeWord
	 * @return char[] positions
	 */
	private int[] arrangeCodeWord(String codeWord) {
		char[] code = codeWord.toCharArray();
		int[] positions = new int[codeWord.length()];
		for (int i = 0; i < positions.length; i++) {
			positions[i] = i;
		}
		for (int i = 0; i < code.length - 1; i++) {
			for (int y = i + 1; y < code.length; y++) {
				if (code[i] < code[y]) {
					char buf = code[i];
					code[i] = code[y];
					code[y] = buf;
					int bufP = positions[i];
					positions[i] = positions[y];
					positions[y] = bufP;
				}
			}
		}

		return positions;
	}

	/**
	 * Reads and loads the filename specified by the user.
	 * 
	 * @param filename
	 * @return loadedFile
	 * @throws Exception
	 */
	public String loadFile(String filename) throws Exception {
		StringBuilder loadedFile = new StringBuilder();
		BufferedReader br = null;

		// I chose a char array to store chunks of text at a time.
		// It was the only way I found that could get the decrypted file to have line breaks and
		// proper formating.
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			char[] cbuf = new char[1024];
			int read = br.read(cbuf);
			while (read != -1) {
				loadedFile.append(cbuf, 0, read);
				read = br.read(cbuf);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} finally {
			if (br != null) {
				br.close();
			}
		}

		return loadedFile.toString();
	}

	/**
	 * Saves the encrypted String to a file.
	 * 
	 * @param encryptedWord
	 * @throws IOException
	 */
	public void saveFile(String encryptedString) throws IOException {
		FileWriter out = null;
		try {
			out = new FileWriter("encryptedText.txt");
			out.write(encryptedString);
			System.out.println("Encrypted file saved to: encryptedText.txt");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	/**
	 * Saves the decrypted String to a file.
	 * 
	 * @param decryptedWord
	 * @throws IOException
	 */
	public void saveDecryptedFile(String decryptedString) throws IOException {
		FileWriter out = null;
		try {
			out = new FileWriter("decryptedText.txt");
			out.write(decryptedString);
			System.out.println("Decrypted file saved to: decryptedText.txt");

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
}
