package ocean.common.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ocean.common.service.EmailSenderService;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-10
 */
@Service
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {
	private JavaMailSender javaMailSender;

	@Autowired
	public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendSimpleEmail(SimpleMailMessage email) {
		try {
			javaMailSender.send(email);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void sendComplexEmail(String[] to, String from, String author, String subject, String text) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from, author);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);

			javaMailSender.send(message);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
