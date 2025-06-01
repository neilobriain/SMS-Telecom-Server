/**
 * 
 */
package p3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Neil Ó Briain
 * 
 * This class is for the creation of an SMS type of message within the system
 */
public class SMS implements MessagingRequired {

	private static final String BOLD_TEXT = "\033[1m";
	private static final String RESET_TEXT = "\033[0m";
	private static final String CONFIDENTUAL_ICON = "\uD83D\uDD10";

	// instance vars
	private int messageId;
	private double destinationAddress;
	private double originatingAddress;
	private LocalDateTime timeStamp;
	private ValidityPeriod validityPeriod;
	private String messageBody;
	private boolean confidentual;

	/**
	 * Default constructor
	 */
	public SMS() {

	}

	/**
	 * Constructor with args
	 * 
	 * @param messageId
	 * @param destinationAddress
	 * @param originatingAddress
	 * @param timeStamp
	 * @param validityPeriod
	 * @param messageBody
	 * @param confidential
	 */
	public SMS(int messageId, double destinationAddress, double originatingAddress, LocalDateTime timeStamp,
			ValidityPeriod validityPeriod, String messageBody, boolean confidentual) {
		this.messageId = messageId;
		this.destinationAddress = destinationAddress;
		this.originatingAddress = originatingAddress;
		this.timeStamp = timeStamp;
		this.validityPeriod = validityPeriod;
		this.messageBody = messageBody;
		this.confidentual = confidentual;
	}

	/**
	 * get the message ID
	 * 
	 * @return the messageId
	 */
	public int getMessageId() {
		return messageId;
	}

	/**
	 * sets the message ID
	 * 
	 * @param messageId the messageId to set
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	/**
	 * gets the destination address
	 * 
	 * @return the destinationAddress
	 */
	public double getDestinationAddress() {
		return destinationAddress;
	}

	/**
	 * sets the destination address
	 * 
	 * @param destinationAddress the destinationAddress to set
	 */
	public void setDestinationAddress(double destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	/**
	 * gets the originating address
	 * 
	 * @return the originatingAddress
	 */
	public double getOriginatingAddress() {
		return originatingAddress;
	}

	/**
	 * sets the originating address
	 * 
	 * @param originatingAddress the originatingAddress to set
	 */
	public void setOriginatingAddress(double originatingAddress) {
		this.originatingAddress = originatingAddress;
	}

	/**
	 * gets the time stamp for message
	 * 
	 * @return the timeStamp
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	/**
	 * sets the time stamp for message
	 * 
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * gets the validity period
	 * 
	 * @return the validityPeriod
	 */
	public ValidityPeriod getValidityPeriod() {
		return validityPeriod;
	}

	/**
	 * sets the validity period
	 * 
	 * @param validityPeriod the validityPeriod to set
	 */
	public void setValidityPeriod(ValidityPeriod validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	/**
	 * gets the message body content
	 * 
	 * @return the messageBody
	 */
	public String getMessageBody() {
		return messageBody;
	}

	/**
	 * sets the message body content
	 * 
	 * @param messageBody the messageBody to set
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	/**
	 * checks if a message is encrypted
	 * 
	 * @return the confidentual
	 */
	public boolean isConfidentual() {
		return confidentual;
	}

	/**
	 * sets if a message is encrypted
	 * 
	 * @param confidentual the confidentual to set
	 */
	public void setConfidentual(boolean confidentual) {
		this.confidentual = confidentual;
	}

	/**
	 * displays the content of the message
	 */
	@Override
	public void displayContent() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		System.out.println(timeStamp.format(formatter));
		int validity = 48; // place holder - ran out of time... enums!!

		System.out.println("---------------------------------------------");
		System.out.println(BOLD_TEXT + "Message ID: " + RESET_TEXT + messageId);
		System.out.printf("%sFrom: %s+%.0f | %sTo: %s+%.0f\n", BOLD_TEXT, RESET_TEXT, originatingAddress, BOLD_TEXT,
				RESET_TEXT, destinationAddress);
		System.out.printf("%sTimestamp %s %s | %sValidity: %s%d hours\n", BOLD_TEXT, RESET_TEXT, timeStamp, BOLD_TEXT,
				RESET_TEXT, validity);

		System.out.print(BOLD_TEXT + "Message: ");
		if (confidentual) {
			System.out.print(CONFIDENTUAL_ICON + " ");
		}
		System.out.print(RESET_TEXT + messageBody + "\n");

		System.out.println("---------------------------------------------");

	}

	/**
	 * returns the message as text
	 * 
	 * @return message as a string
	 */
	@Override
	public String messageToText() {
		return messageId + "," + destinationAddress + "," + originatingAddress + "," + timeStamp + "," + validityPeriod
				+ " " + messageBody + "\n";
	}

}
