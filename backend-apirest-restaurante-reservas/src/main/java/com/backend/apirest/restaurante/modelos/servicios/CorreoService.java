package com.backend.apirest.restaurante.modelos.servicios;

import com.backend.apirest.restaurante.modelos.entidades.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class CorreoService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private TemplateEngine templateEngine;

	public void sendEmailFromTemplate(Reserva reserva) throws MessagingException {
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setTo(reserva.getEmail());
			helper.setSubject("Confirmación de reserva");
			
			Context context = new Context();
			context.setVariable("nombre", reserva.getNombres());
			context.setVariable("fechareserva", reserva.getFecha_reserva());
			context.setVariable("cantidadpersonas", reserva.getCantidad_personas());
			context.setVariable("tiporeserva", reserva.getTipo_reserva());
			String contentHTML = templateEngine.process("reserva", context);
			
			helper.setText(contentHTML, true);

			mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
