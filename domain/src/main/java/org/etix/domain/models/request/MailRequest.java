package org.etix.domain.models.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MailRequest {

    private String object;
    private String content;
    private String[] recipients;
    private List<String> attachements = new ArrayList<>();

    public MailRequest() {
    }

    public MailRequest(String object, String content, String[] recipients, List<String> attachements) {
        this.object = object;
        this.content = content;
        this.recipients = recipients;
        this.attachements = attachements;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public List<String> getAttachements() {
        return attachements;
    }

    public void setAttachements(List<String> attachements) {
        this.attachements = attachements;
    }

    @Override
    public String toString() {
        return "MailRequest{" +
                "object='" + object + '\'' +
                ", content='" + content + '\'' +
                ", recipients=" + Arrays.toString(recipients) +
                ", attachements=" + attachements +
                '}';
    }
}
