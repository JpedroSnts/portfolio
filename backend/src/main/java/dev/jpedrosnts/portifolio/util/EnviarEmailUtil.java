package dev.jpedrosnts.portifolio.util;


import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailContent;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailRecipients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EnviarEmailUtil {

	private final String connectionString;
	private final String emailSender;

	@Autowired
	public EnviarEmailUtil(
			@Value("${azure.email.connection.string}") String connectionString,
			@Value("${azure.email.sender}") String emailSender
	) {
		this.connectionString = connectionString;
		this.emailSender = emailSender;
	}

	public void enviar(String assunto, String mensagem, String[] emails) {
		EmailClient emailClient = new EmailClientBuilder()
				.connectionString(connectionString)
				.buildClient();
		EmailContent content = new EmailContent(assunto)
				.setPlainText(mensagem);


		ArrayList<EmailAddress> addressList = new ArrayList<>();
		for (String email : emails) {
			EmailAddress emailAddress = new EmailAddress(email);
			addressList.add(emailAddress);
		}

		EmailRecipients emailRecipients = new EmailRecipients(addressList);
		EmailMessage emailMessage = new EmailMessage(emailSender, content);
		emailMessage.setRecipients(emailRecipients);

		emailClient.send(emailMessage);
	}
}
