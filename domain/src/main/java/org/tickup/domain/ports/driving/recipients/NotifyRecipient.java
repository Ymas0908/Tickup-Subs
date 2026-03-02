package org.tickup.domain.ports.driving.recipients;


import org.tickup.domain.apiRequest.MailRequest;

import java.io.IOException;
import java.util.Map;

public interface NotifyRecipient {

    Boolean envoyerMail(MailRequest mRequest);
    String formatMail(String string, Map<String, String> messageParams) throws IOException;



}
