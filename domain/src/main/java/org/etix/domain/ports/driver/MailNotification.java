package org.etix.domain.ports.driver;



import org.etix.domain.models.request.MailRequest;

import java.io.IOException;
import java.util.Map;

public interface MailNotification {

    boolean envoyer(MailRequest mRequest);

    String format(String string, Map<String, String> messageParams) throws IOException;

}
