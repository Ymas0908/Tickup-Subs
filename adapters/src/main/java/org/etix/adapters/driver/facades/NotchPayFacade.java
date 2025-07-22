//package org.etix.adapters.driver.facades;
//
//import org.etix.domain.request.NotchPayRequest;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class NotchPayFacade {
//
//    private NotchPayPort notchPayPort;
//    private final String callbackUrl;
//
//
//
//
//
//
//    public NotchPayFacade(NotchPayPort notchPayPort, @Value("https://api.notchpay.co")String callbackUrl) {
//        this.notchPayPort = notchPayPort;
//        this.callbackUrl = callbackUrl;
//    }
//
//    /**
//     * initier paiement
//     *
//     * @param notchPayRequest
//     */
//    public void initierPaiement(NotchPayRequest notchPayRequest) {
//        notchPayRequest.setCallbackUrl(callbackUrl);
//        notchPayPort.initierPaiement(notchPayRequest);
//    }
//
//}
//
