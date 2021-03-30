package ie.gmit.dip;

import java.util.Scanner;

public class Menu {

	private static Scanner scanner = new Scanner(System.in);
	private ADFGVX cypher;

	public Menu() {
		this.cypher = new ADFGVX();
	}

	/**
	 * Basic user interface and menu for the application
	 * 
	 */
	public void start() {

		boolean quit = false;
		printActions();
		while (!quit) {
			System.out.println("Enter action: " + "1-encrypt/decrypt; 2-encrypt/decrypt from file; 0-quit");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 0:
				System.out.println("Shutting down...");
				quit = true;
				break;
			case 1:
				encryptDecryptText();
				break;
			case 2:
				encryptTextFromFile();
				break;
			default:
				System.out.println("Invalid Input - Try again");
			}
		}
	}

	private void printActions() {
		System.out.println("\n********** Polybius Square Cypher **********");
		System.out.println(
				"0 - to shutdown\n" + "1 - to encrypt / decrypt text\n" + "2 - to encrypt / decrypt text from existing file\n");
	}

	/**
	 * Encrypt/Decrypt sub-menu. Displays the finished results on screen.
	 * It adds an additional timer to check the algorithm's speed.
	 * 
	 */
	private void encryptDecryptText() {
				System.out.println("Enter text to be encrypted: ");
				String text = scanner.nextLine();
				System.out.println("Enter the key:");
				String key = scanner.nextLine();
				long startTime = System.currentTimeMillis();
				System.out.println("Encrypted text is:");
				String encryptedText = cypher.encryptText(text, key);
				System.out.println(encryptedText);
				System.out.println("Decrypted text is:");
				String decryptedFile = cypher.decryptText(encryptedText, key);
				System.out.println(decryptedFile);
				System.out.println("Running time (milliseconds): " + (System.currentTimeMillis() - startTime));			
			}

	/**
	 * Encrypt/Decrypt from file sub-menu. Gives the user a choice from 8 text files.
	 * Loads and saves the encrypted/decrypted files.
	 * It adds an additional timer to check the algorithm's speed.
	 */
	private void encryptTextFromFile() {

		boolean quit = false;
		String filename = null;
		int choice = 0;

		while (!quit) {
			System.out.println("Choose from the following: \n");
			System.out.println(
					"0 - to return to previous menu\n" + "1 - DeBelloGallico-Caesar\n" + "2 - DivineComedy-Dante\n"
							+ "3 - HappyPrince-Wilde\n" + "4 - IgnoreWords\n" + "5 - PictureOfDorianGray-Wilde\n"
							+ "6 - PoblachtNaHEireann\n" + "7 - ThePrince-Machiavelli\n" + "8 - WarAndPeace-Tolstoy");
			System.out.println("Enter action");

			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 0:
				System.out.println("Returning to main menu...");
				quit = true;
				break;
			case 1:
				//filename = "test.txt";
				filename = "DeBelloGallico-Caesar.txt";
				quit = true;
				break;
			case 2:
				filename = "DivineComedy-Dante.txt";
				quit = true;
				break;
			case 3:
				filename = "HappyPrince-Wilde.txt";
				quit = true;
				break;
			case 4:
				filename = "IgnoreWords.txt";
				quit = true;
				break;
			case 5:
				filename = "PictureOfDorianGray-Wilde.txt";
				quit = true;
				break;
			case 6:
				filename = "PoblachtNaHEireann.txt";
				quit = true;
				break;
			case 7:
				filename = "ThePrince-Machiavelli.txt";
				quit = true;
				break;
			case 8:
				filename = "WarAndPeace-Tolstoy.txt";
				quit = true;
				break;
			default:
				System.out.println("Invalid Input - Try again");
				break;
			}
		}
		if (choice > 0 & choice <= 8) {
			try {
				System.out.println("Enter the key:");
				String key = scanner.nextLine();
				long startTime = System.currentTimeMillis();
				String text = cypher.loadFile(filename);
				String encryptedWord = cypher.encryptText(text, key);
				cypher.saveFile(encryptedWord);
				String encryptedText = cypher.loadFile("encryptedText.txt");
				String decryptedFile = cypher.decryptText(encryptedText, key);
				cypher.saveDecryptedFile(decryptedFile);
				System.out.println("Running time (milliseconds): " + (System.currentTimeMillis() - startTime));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Returning to main menu...");
		}

	}
}
