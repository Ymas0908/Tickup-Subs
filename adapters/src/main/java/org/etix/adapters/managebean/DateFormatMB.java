package org.etix.adapters.managebean;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@SessionScope
@ManagedBean("dateFormatMB")
public class DateFormatMB implements Serializable {

    private static final long serialVersionUID = -5916213736521318251L;

    @Getter
    private String local, localDate, localDateTime, date, dateTime, dateTest;

    @PostConstruct
    public void init() {
        this.localDate = "localDate";
        this.localDateTime = "localDateTime";
        this.local = "fr-FR";
        this.date = "dd/MM/yyyy";
        this.dateTest = "yyyy/MM//dd";
        this.dateTime = "dd/MM/yyyy  HH:mm";
    }

}
