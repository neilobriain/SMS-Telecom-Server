/**
 * 
 */
package p3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Neil Ó Briain
 * 
 * Application main control class
 */
public class MessageServer {

	private static final String DATA_FILE = "sms_message.csv";
	private static final int MESSAGE_TRUNCATE_LIMIT = 20;
	public static final String SENT_FILE = "message_sent.csv";
	public static Queue<SMS> messages = new LinkedList<SMS>();

	/**
	 * Start point for application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			System.out.println("Message server started");
			readData(DATA_FILE);

			writeFile();

			System.out.println("Program completed");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * writes message contents to new file using new thread
	 * 
	 * @param sentFile
	 */
	public static void writeFile() {
		Thread thread = new Thread(new MessageText());
		thread.start();

	}

	/**
	 * Store data from specified CSV into "messages" queue
	 * 
	 * @param dataFile
	 */
	public static void readData(String dataFile) throws IllegalArgumentException {
		try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE));) {

			String line = br.readLine(); // skip header line
			String data[]; // for parsing lines
			line = br.readLine(); // read first line

			// set up variables for SMS
			int messageId;
			double destinationAddress;
			double originatingAddress;
			LocalDateTime timeStamp;
			ValidityPeriod validityPeriod;
			String messageBody;
			boolean confidentual;

			System.out.println("Loading data ...");

			int attemptCount = 0;
			int successCount = 0;

			// read each line from file and build queue
			while (line != null) {
				attemptCount++;
				data = line.split(",");

				// print raw data to screen
				System.out.println(Arrays.toString(data));

				messageId = Integer.parseInt(data[0].trim());
				destinationAddress = Double.parseDouble(data[1].trim());
				originatingAddress = Double.parseDouble(data[2].trim());
				timeStamp = LocalDateTime.parse(data[3]);

				// Set validity Period
				switch (Integer.parseInt(data[4])) {
				case 12:
					validityPeriod = ValidityPeriod.TWELVE;
					break;
				case 24:
					validityPeriod = ValidityPeriod.TWENTYFOUR;
					break;
				case 48:
					validityPeriod = ValidityPeriod.FORTYEIGHT;
					break;
				default:
					throw new IllegalArgumentException("Invalid Validity Period in Data");
				}

				messageBody = data[5]; // this may be altered for truncation or decryption before being added to queue

				// check if message is confidentual
				if (Integer.parseInt(data[6]) == 1) {
					confidentual = true;
				} else {
					confidentual = false;
				}

				// decrypt if needed
				if (confidentual) {
					messageBody = decryptMessage(messageBody);
				}

				// truncate message if needed
				if (messageBody.length() > MESSAGE_TRUNCATE_LIMIT) {
					messageBody = truncateMessage(messageBody);
				}

				// set up checkValidity
				LocalDateTime checkValidity = timeStamp;
				if (validityPeriod.equals(ValidityPeriod.FORTYEIGHT)) {
					checkValidity.plusHours(48);
				} else if (validityPeriod.equals(ValidityPeriod.TWENTYFOUR)) {
					checkValidity.plusHours(24);
				} else if (validityPeriod.equals(ValidityPeriod.TWELVE)) {
					checkValidity.plusHours(12);
				}

				// add to the SMS messages queue if their time is still valid
				if (LocalDateTime.now().isAfter(checkValidity)) {
					// add message to queue
					messages.add(new SMS(messageId, destinationAddress, originatingAddress, timeStamp, validityPeriod,
							messageBody, confidentual));
					successCount++;
				}

				line = br.readLine(); // read next line
			}

			System.out.println();
			System.out.println("Attempted to read messages " + attemptCount);
			System.out.println("Successful messages added " + successCount);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Decrypts the message body and returns the unencrypted text
	 * 
	 * @param messageBody
	 * @return
	 */
	public static String decryptMessage(String data) {
		StringBuilder decrypted = new StringBuilder();
		for (char c : data.toCharArray()) {
			if (Character.isLetter(c)) { // Shift only letters

				decrypted.append((char) (c - 1));

			} else {
				decrypted.append(c); // Keep special characters unchanged
			}
		}
		return decrypted.toString();
	}

	/**
	 * Truncates a messsage body to the truncate limit amount and returns the
	 * truncated text
	 * 
	 * @param messageBody
	 * @return
	 */
	public static String truncateMessage(String message) {
		if (message.length() > MESSAGE_TRUNCATE_LIMIT) {
			return message.substring(0, MESSAGE_TRUNCATE_LIMIT - 3) + "...";
		} else {
			return message;
		}
	}

}
