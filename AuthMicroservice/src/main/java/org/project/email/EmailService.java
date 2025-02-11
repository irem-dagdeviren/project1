package org.project.email;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.project.config.CustomProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    JavaMailSenderImpl mailSender;
    @Autowired
    CustomProperties customProperties;
    @Autowired
    private MessageSource messageSource;


    @PostConstruct
    public void initJavaMailSender() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(customProperties.getEmail().host());
        mailSender.setPort(customProperties.getEmail().port());
        mailSender.setUsername(customProperties.getEmail().username());
        mailSender.setPassword(customProperties.getEmail().password());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", customProperties.getEmail().protocol());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
    }

    String activationEmail = """
            <html>
                <body>
                    <h1> ${title}</h1>
                    <a href="${url}">${click}</a>
                </body>
            </html>
            """;

    public void sendActivationMail(String email, String activationCode) {
        var activationUrl = customProperties.getClient().host() + "/activation/" + activationCode;
        var title = messageSource.getMessage("activate.mail.title", null, LocaleContextHolder.getLocale());
        var clickHere = messageSource.getMessage("activate.mail.click.here", null, LocaleContextHolder.getLocale());
        var mailBody = activationEmail
                .replace("${url}", activationUrl)
                .replace("${title}", title)
                .replace("${click}", clickHere);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        try {
            message.setFrom(customProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);
        }catch (MessagingException e) {
            e.printStackTrace();
        }
        this.mailSender.send(mimeMessage);

    }


}
