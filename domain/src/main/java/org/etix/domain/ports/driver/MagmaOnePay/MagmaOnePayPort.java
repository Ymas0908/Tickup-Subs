package org.etix.domain.ports.driver.MagmaOnePay;

import org.etix.domain.request.MagmaOnePayRequest;

public interface MagmaOnePayPort {
    void effectuerPaiement(MagmaOnePayRequest magmaOnePayRequest);

}
