package com.sudoku.solver.service;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.sudoku.solver.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AmazonSESSample implements InitializingBean {

    //    @Value("classpath:templates/welcome-email.html")
//    Resource htmlFile;
    private final SpringTemplateEngine templateEngine;
    static final String FROM = "bkasina@leomail.tamuc.edu";

    // The subject line for the email.
    static final String SUBJECT = "Solved Sudoku Puzzle";


    private AmazonSimpleEmailService client;

    @Override
    public void afterPropertiesSet() throws Exception {
        client = AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(Regions.US_EAST_2).build();
    }

    public boolean sendEmail(EmailRequest emailRequest) {
        try {
            Map<String, Object> properties = new HashMap<>();
            properties.put("grid", emailRequest.getSolvedPuzzle());
            Context context = new Context();
            context.setVariables(properties);
            String templateFile = "welcome-email.html";
            String html = templateEngine.process(templateFile, context);
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(emailRequest.getToEmailAddress()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(html)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(FROM);
            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
        return true;
    }


}