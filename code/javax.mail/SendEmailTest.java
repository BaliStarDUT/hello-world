import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.MessagingException;

// import javax.mail.Message.RecipientType;

class SendEmailTest{
  public static void main(String[] args){
      // Recipient's email ID needs to be mentioned.
      String to = "drawnkid@yeah.net";
      // Sender's email ID needs to be mentioned
      String from = "drawnkid@yeah.net";
      // Assuming you are sending email from localhost
     String host = "localhost";
     // Get system properties
     Properties properties = System.getProperties();
     // Setup mail server
     properties.setProperty("mail.smtp.host", host);
     properties.setProperty("mail.smtp.port", "32769");

     properties.setProperty("mail.protocol.host", host);
     properties.setProperty("mail.protocol.port", "32769");
    //  properties.setProperty("mail.protocol.user", "host");

     // Get the default Session object.
     Session session = Session.getDefaultInstance(properties);
     try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));
         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));
         // Set Subject: header field
         message.setSubject("This is the Subject Line!");
         // Now set the actual message
         message.setText("This is actual message");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
  }
}
