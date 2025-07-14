package proyecto.email;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class EnviarCorreo {
    private  String remitente;
    private  String appPassword;
    private  String destinatario;
    private Session session;
    public EnviarCorreo() {
        Dotenv dotenv = Dotenv.load();
        this.remitente = dotenv.get("MAIL_USER");
        this.appPassword = dotenv.get("APP_PASSWORD");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        this.session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, appPassword);
            }
        });
    }

    public void enviarCorreo(String destinatario, String asunto, String mensaje) {
        this.destinatario = destinatario;
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);
            System.out.println("Correo enviado exitosamente a " + destinatario);
        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
