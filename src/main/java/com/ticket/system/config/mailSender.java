package com.ticket.system.config;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class mailSender {

	private static final String fromMail = "";
	
	public Mail buildEmail(Long ticketId, String userMail, String response) {
	    Email from = new Email(fromMail);
	    String subject = "Response for the Ticket Id :: "+ticketId;
	    Email to = new Email(userMail);
	    Content content = new Content("text/plain", response);
	    Mail mail = new Mail(from, subject, to, content);

	    return mail;
	  }

	  public void baselineExample(Long ticketId, String userMail, String response) throws IOException {
	    final Mail helloWorld = buildEmail(ticketId, userMail, response);
	    send(helloWorld);
	  }

	  private static void send(final Mail mail) throws IOException {
	    final SendGrid sg = new SendGrid("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");
	    final Request request = new Request();
	    request.setMethod(Method.POST);
	    request.setEndpoint("mail/send");
	    request.setBody(mail.build());

	    final Response response = sg.api(request);
	    System.out.println(response.getStatusCode());
	    //System.out.println(response.getBody());
	    //System.out.println(response.getHeaders());
	  }
}
