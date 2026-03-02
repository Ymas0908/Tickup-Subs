package org.tickup.adapters.ports.driver.facades;

import org.tickup.domain.apiRequest.MailRequest;
import org.tickup.domain.ports.driver.NotifyDriver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
@Component
public class NotifyFacade {

    private final NotifyDriver notifyDriver;

    public void envoyerMail(MailRequest mailRequest) {
        notifyDriver.envoyerMail(mailRequest);
    }
    public String formatMail(String name, Map<String, String> params) throws IOException {
        return notifyDriver.formatMail(name, params);
    }



}
