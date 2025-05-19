package org.etix.adapters.driver.facades;

import org.etix.domain.ports.driver.Paytech.PaytechPayementPort;
import org.etix.domain.request.PaytechPaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaytechPaymentFacade {

    private PaytechPayementPort paytechPayementPort;
    private final String callbackUrl;



    public PaytechPaymentFacade(PaytechPayementPort paytechPayementPort,@Value("https://paytech.sn/api") String callbackUrl) {
        this.paytechPayementPort = paytechPayementPort;
        this.callbackUrl = callbackUrl;
    }

    /**
     * initier paiement
     *
     * @param paytechPaymentRequest
     */
    public void initierPaiement(PaytechPaymentRequest paytechPaymentRequest) {
        paytechPaymentRequest.setCallbackUrl(callbackUrl);
        paytechPayementPort.initierPaiement(paytechPaymentRequest);
    }





}
