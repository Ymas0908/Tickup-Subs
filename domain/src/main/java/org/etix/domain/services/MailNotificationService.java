//package org.etix.domain.services;
//
//
//
//import org.etix.domain.ddd.DomaineService;
//import org.etix.domain.models.request.MailRequest;
//import org.etix.domain.models.response.MailResponse;
//import org.etix.domain.ports.in.MailNotification;
//import org.etix.domain.ports.out.MailNotificationRecipient;
//
//import java.io.IOException;
//import java.util.Map;
//
//@DomaineService
//public class MailNotificationService implements MailNotification {
//
//    private final MailNotificationRecipient mailNotificationRecipient;
//
//
//    public MailNotificationService(MailNotificationRecipient mailNotificationRecipient) {
//        this.mailNotificationRecipient = mailNotificationRecipient;
//    }
//
//    @Override
//    public boolean envoyer(MailRequest mRequest) {
//        MailResponse result = mailNotificationRecipient.envoyer(mRequest);
//        return result.getStatus() == 200;
//    }
//
//    @Override
//    public String format(String string, Map<String, String> messageParams) throws IOException {
//        return mailNotificationRecipient.format(string, messageParams);
//    }
//}
