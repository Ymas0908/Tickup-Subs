package org.etix.adapters.notification;

import java.io.IOException;
import java.util.Map;

public interface MailNotification {

    MailResponse send(MailRequest mRequest);

    String format(String string, Map<String, String> messageParams) throws IOException;

}
