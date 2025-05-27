package org.etix.domain.ports.driver;

import org.etix.domain.request.NotchPayRequest;

public interface NotchPayPort {

    void initierPaiement(NotchPayRequest notchPayRequest);
}
