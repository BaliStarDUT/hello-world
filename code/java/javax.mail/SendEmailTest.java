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
      String to = "bbbbb@yeah.net";
      // Sender's email ID needs to be mentioned
      String from = "aaaa@qq.com";
      // Assuming you are sending email from localhost
     String host = "smtp.qq.com";
     // Get system properties
     Properties properties = System.getProperties();
     // Setup mail server
     properties.setProperty("mail.user","aaa@qq.com");
     properties.setProperty("mail.password","aaaaaa");

     properties.setProperty("mail.smtp.host", host);
     properties.setProperty("mail.smtp.port", "465");
     properties.setProperty("mail.smtp.auth","true");
     properties.setProperty("mail.smtp.socketFactory.port","465");
     properties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
     properties.setProperty("mail.smtp.socketFactory.fallback","false");
     properties.setProperty("mail.protocol.host", host);
     properties.setProperty("mail.protocol.port", "465");

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
         Transport transport = session.getTransport("smtp");
         transport.connect(host, from, "mrvmktmrtcwpbcce");
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();

        //  Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
  }
}
