//package org.etix.adapters.driver.facades;
//
//import lombok.AllArgsConstructor;
//import org.etix.domain.ports.driver.CinetPay.CinetPayPort;
//import org.etix.domain.request.CinetPayRequest;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CineyPayFacade {
//
//    private CinetPayPort cinetPayPort;
//    private final String callbackUrl;
//
//
//
//
//    public CineyPayFacade(CinetPayPort cinetPayPort, @Value("https://api-checkout.cinetpay.com")String callbackUrl) {
//        this.cinetPayPort = cinetPayPort;
//        this.callbackUrl = callbackUrl;
//    }
//
//    /**
//     * initier paiement
//     *
//     * @param cinetPayRequest
//     */
//    public void initierPaiement(CinetPayRequest cinetPayRequest) {
//        cinetPayRequest.setCallbackUrl(callbackUrl);
//        cinetPayPort.initierPaiement(cinetPayRequest);
//    }
//
//
//
//
//
//}
