package org.etix.domain.ports.driving;

import org.etix.domain.request.NotchPayRequest;

public interface NotchPayRepo {

    void initierPaiement(NotchPayRequest notchPayRequest);
}
