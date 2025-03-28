package com.gnr.ecommorce.notification.email;

import com.gnr.ecommorce.notification.emai.template.EmailTemplate;
import com.gnr.ecommorce.notification.kafka.order.record.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final SpringTemplateEngine springTemplateEngine;

    @Override
    @Async
    public void paymentSuccessEmail(
            String toMail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message
                , MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED
                , StandardCharsets.UTF_8.name());
        messageHelper.setFrom("contact@gmail.com");
        final String templateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplate.PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = springTemplateEngine.process(
                    EmailTemplate.PAYMENT_CONFIRMATION.getTemplate()
                    , context
            );
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(toMail);
            javaMailSender.send(message);
            log.info(
                    String.format("INFO - Email successfully sent to %s with template %s,", toMail, templateName)
            );
        } catch (MessagingException messagingException) {
            log.warn("WARNING - Cannot send email to {}", toMail);
        }
    }


    @Override
    @Async
    public void orderSuccessEmail(
            String toMail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message
                , MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED
                , StandardCharsets.UTF_8.name());
        messageHelper.setFrom("contact@gmail.com");
        final String templateName = EmailTemplate.ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = springTemplateEngine.process(
                    EmailTemplate.ORDER_CONFIRMATION.getTemplate()
                    , context
            );
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(toMail);
            javaMailSender.send(message);
            log.info(
                    String.format("INFO - Email successfully sent to %s with template %s,", toMail, templateName)
            );
        } catch (MessagingException messagingException) {
            log.warn("WARNING - Cannot send email to {}", toMail);
        }
    }
}
