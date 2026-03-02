package org.tickup.domain.services;

import org.tickup.domain.apiRequest.MailRequest;
import org.tickup.domain.ddd.DomaineService;
import org.tickup.domain.ports.driving.recipients.NotifyRecipient;
import org.tickup.domain.ports.driver.NotifyDriver;

import java.io.IOException;
import java.util.Map;

@DomaineService

public class NotifyService implements NotifyDriver {
    private final NotifyRecipient notifyRecipient;

    public NotifyService(NotifyRecipient notifyRecipient) {
        this.notifyRecipient = notifyRecipient;
    }


    @Override
    public void envoyerMail(MailRequest mailRequest) {
        notifyRecipient.envoyerMail(mailRequest);
    }

    @Override
    public String formatMail(String name, Map<String, String> params) throws IOException {
        return notifyRecipient.formatMail(name, params);
    }
}
