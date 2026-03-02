package org.tickup.domain.apiRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MailRequest {

    private String object;
    private String content;
    private String[] recipients;
    private List<AttachmentBase64> attachments = new ArrayList<>();

    public MailRequest() {
    }

    public MailRequest(String object, String content, String[] recipients, List<AttachmentBase64> attachments) {
        this.object = object;
        this.content = content;
        this.recipients = recipients;
        this.attachments = attachments;
    }

    private MailRequest(Builder builder) {
        setObject(builder.object);
        setContent(builder.content);
        setRecipients(builder.recipients);
        attachments = builder.attachments;
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

    public List<AttachmentBase64> getAttachments() {
        return attachments;
    }

    public void setAttachements(List<AttachmentBase64> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "MailRequest{" +
                "object='" + object + '\'' +
                ", content='" + content + '\'' +
                ", recipients=" + Arrays.toString(recipients) +
                ", attachements=" + attachments +
                '}';
    }


    public static final class Builder {
        private String object;
        private String content;
        private String[] recipients;
        private List<AttachmentBase64> attachments;

        public Builder() {
        }

        public Builder object(String val) {
            object = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder recipients(String[] val) {
            recipients = val;
            return this;
        }

        public Builder attachments(List<AttachmentBase64> val) {
            attachments = val;
            return this;
        }

        public MailRequest build() {
            return new MailRequest(this);
        }
    }
}
