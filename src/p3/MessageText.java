/**
 * 
 */
package p3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Neil Ó Briain
 */
public class MessageText implements Runnable {

	@Override
	public void run() {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(MessageServer.SENT_FILE));) {

			bw.write("MessageID,DestinationAddress,OriginatingAddress,MessageBody");

			for (SMS sms : MessageServer.messages) {
				bw.write(sms.messageToText());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
