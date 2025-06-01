/**
 * 
 */
package p3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Neil Ó Briain
 * Test cases for SMS class
 */
class SMSTest {
	
	int messageIdValid;
	double destinationAddressValid;
	double originatingAddressValid;
	LocalDateTime timeStampValid;
	ValidityPeriod validityPeriodValid;
	String messageBodyValid;
	boolean confidentualValid;
	SMS sms;
	

	/**
	 * Set up variables before each test
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		messageIdValid=12345;
		destinationAddressValid=12345;
		originatingAddressValid=12345;
		LocalDateTime timeStampValid = LocalDateTime.parse("2025-03-10T16:38:56.349");
		ValidityPeriod validityPeriodValid = ValidityPeriod.FORTYEIGHT;
		messageBodyValid="message";
		boolean confidentualValid=true;
		sms=new SMS();
	}

	/**
	 * test default constructor
	 */
	@Test
	void testDefaultConstructor() {
		sms=new SMS();
		assertNotNull(sms);
	}	
	
	/**
	 * test constructor with valid args
	 */
	@Test
	void testConstructorWithArgsValid() {
		sms=new SMS(messageIdValid, destinationAddressValid, originatingAddressValid, timeStampValid,
				validityPeriodValid, messageBodyValid, confidentualValid);
		assertNotNull(sms);
	}
	
	/**
	 * test valid getter and setter for messageId
	 */
	@Test
	void testSetGetMessageIdValid() {
		int expected = messageIdValid;
		sms.setMessageId(messageIdValid);
		int actual = sms.getMessageId();
		assertEquals(expected, actual);
	}

	/**
	 * test valid getter and setter for destination address
	 */
	@Test
	void testSetGetDestinationAddressValid() {
		double expected = destinationAddressValid;
		sms.setDestinationAddress(destinationAddressValid);
		double actual = sms.getDestinationAddress();
		assertEquals(expected, actual);
	}
	
	/**
	 * test valid getter and setter for originating address
	 */
	@Test
	void testSetGetOriginatingAddressValid() {
		double expected = originatingAddressValid;
		sms.setOriginatingAddress(originatingAddressValid);
		double actual = sms.getOriginatingAddress();
		assertEquals(expected, actual);
	}
	
	/**
	 * test valid getter and setter for messageId
	 */
	@Test
	void testSetGetTimeStampValid() {
		LocalDateTime expected = timeStampValid;
		LocalDateTime toSet = LocalDateTime.parse("2025-03-10T16:38:56.349");
		sms.setTimeStamp(toSet);
		LocalDateTime actual = sms.getTimeStamp();
		assertEquals(expected, actual);
	}
	
	/**
	 * test valid getter and setter for validity period
	 */
	@Test
	void testSetGetValidityPeriodValid() {
		ValidityPeriod expected = validityPeriodValid;
		sms.setValidityPeriod(expected);
		ValidityPeriod actual = sms.getValidityPeriod();
		assertEquals(expected, actual);
	}
	
	/**
	 * test valid getter and setter for message body
	 */
	@Test
	void testSetGetMessageBodyValid() {
		String expected = messageBodyValid;
		sms.setMessageBody(expected);
		String actual = sms.getMessageBody();
		assertEquals(expected, actual);
	}
	
	/**
	 * test valid getter and setter for confidential variable
	 */
	@Test
	void testSetGetConfidentialValid() {
		boolean expected = confidentualValid;
		sms.setConfidentual(confidentualValid);
		boolean actual =sms.isConfidentual();
		assertEquals(expected, actual);
	}


}
