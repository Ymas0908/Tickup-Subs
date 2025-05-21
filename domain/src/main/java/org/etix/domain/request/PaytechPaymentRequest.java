package org.etix.domain.request;

public class PaytechPaymentRequest {
    private String item_name;
    private double item_price;
    private String command_name;

    private String ref_command;

    private String callbackUrl;


    public PaytechPaymentRequest() {
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public String getCommand_name() {
        return command_name;
    }

    public void setCommand_name(String command_name) {
        this.command_name = command_name;
    }

    public String getRef_command() {
        return ref_command;
    }

    public void setRef_command(String ref_command) {
        this.ref_command = ref_command;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
