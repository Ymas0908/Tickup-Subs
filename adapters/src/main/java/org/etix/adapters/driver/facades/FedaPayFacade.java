package org.etix.adapters.driver.facades;

import org.etix.domain.ports.driver.FedaPay.FedaPayPort;
import org.etix.domain.request.FedaPayRequest;
import org.etix.domain.request.PaytechPaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FedaPayFacade {

    private FedaPayPort fedaPayPort;
    private final String callbackUrl;



    public FedaPayFacade(FedaPayPort fedaPayPort,@Value("https://sandbox-api.fedapay.com/v1") String callbackUrl) {
        this.fedaPayPort = fedaPayPort;
        this.callbackUrl = callbackUrl;
    }

    /**
     * initier paiement
     *
     * @param fedaPayRequest
     */
    public void initierPaiement(FedaPayRequest fedaPayRequest) {
        fedaPayRequest.setCallbackUrl(callbackUrl);
        fedaPayPort.initierPaiement(fedaPayRequest);
    }





}
