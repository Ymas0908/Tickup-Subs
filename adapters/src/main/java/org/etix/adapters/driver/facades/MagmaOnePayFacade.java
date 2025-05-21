package org.etix.adapters.driver.facades;

import org.etix.domain.ports.driver.MagmaOnePay.MagmaOnePayPort;
import org.etix.domain.request.CinetPayRequest;
import org.etix.domain.request.MagmaOnePayRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MagmaOnePayFacade {

    private MagmaOnePayPort magmaOnePayPort;
    private final String callbackUrl;




    public MagmaOnePayFacade(MagmaOnePayPort magmaOnePayPort, @Value("https://api.magmaonepay.com/v1/payment/init")String callbackUrl) {
        this.magmaOnePayPort = magmaOnePayPort;
        this.callbackUrl = callbackUrl;
    }

    /**
     * initier paiement
     *
     * @param magmaOnePayRequest
     */
    public void effectuerPaiement(MagmaOnePayRequest magmaOnePayRequest) {
        magmaOnePayRequest.setCallbackUrl(callbackUrl);
        magmaOnePayPort.effectuerPaiement(magmaOnePayRequest);
    }





}
