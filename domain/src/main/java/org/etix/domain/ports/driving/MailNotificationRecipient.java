package org.etix.domain.ports.driving;



import org.etix.domain.models.request.MailRequest;
import org.etix.domain.models.response.MailResponse;

import java.io.IOException;
import java.util.Map;

public interface MailNotificationRecipient {
    MailResponse envoyer(MailRequest mRequest);

    String format(String name, Map<String, String> params) throws IOException;
}
