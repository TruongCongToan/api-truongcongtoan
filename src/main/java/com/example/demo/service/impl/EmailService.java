package com.example.demo.service.impl;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.example.demo.model.EmailDTO;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private FreeMarkerConfigurer freemarkerConfig;

	public void sendWelcomeEmail(EmailDTO emailDTO) {
		System.out.println("##### Started sending welcome email ####");

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			String templateContent = FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfig.getConfiguration()
							.getTemplate("/email/doctor.ftlh"),
							emailDTO.getEmailData());

			helper.setTo(emailDTO.getTo());
			helper.setSubject(emailDTO.getSubject());
			helper.setText(templateContent, true);
			mailSender.send(mimeMessage);

			System.out.println("######## Welcome email sent ######");
		} catch (Exception e) {
			System.out.println("Sending welcome email failed, check log...");
			e.printStackTrace();
		}
	}
}