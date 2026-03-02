package org.etix.adapters.notification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
public class MailRequest {

    private String object;
    private String content;
    private String[] recipients;
    private List<String> attachements = new ArrayList<>();

}
