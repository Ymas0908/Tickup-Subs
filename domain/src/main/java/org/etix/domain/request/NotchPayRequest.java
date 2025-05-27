package org.etix.domain.request;

import org.etix.domain.models.Customer;

public class NotchPayRequest {

    private String id;
    private String reference;
    private int amount;
    private String currency;
    private Customer customer;
    private String payment_method;
    private String description;

    private String callbackUrl;

    public NotchPayRequest() {
    }

    public NotchPayRequest(String id, String reference, int amount, String currency, Customer customer, String payment_method, String description, String callbackUrl) {
        this.id = id;
        this.reference = reference;
        this.amount = amount;
        this.currency = currency;
        this.customer = customer;
        this.payment_method = payment_method;
        this.description = description;
        this.callbackUrl = callbackUrl;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
