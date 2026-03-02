package org.tickup.domain.ports.driver;


import org.tickup.domain.apiRequest.MailRequest;

import java.io.IOException;
import java.util.Map;

public interface NotifyDriver {
    void envoyerMail(MailRequest mailRequest);
    String formatMail(String name, Map<String, String> params) throws IOException;


}
