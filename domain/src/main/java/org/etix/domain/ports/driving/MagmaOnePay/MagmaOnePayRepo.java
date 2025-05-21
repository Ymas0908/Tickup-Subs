package org.etix.domain.ports.driving.MagmaOnePay;

import org.etix.domain.request.MagmaOnePayRequest;

public interface MagmaOnePayRepo {

    void effectuerPaiement(MagmaOnePayRequest magmaOnePayRequest);
}
