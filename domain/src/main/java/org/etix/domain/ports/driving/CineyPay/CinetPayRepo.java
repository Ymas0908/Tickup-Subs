package org.etix.domain.ports.driving.CineyPay;

import org.etix.domain.request.CinetPayRequest;

public interface CinetPayRepo {

    void initierPaiement(CinetPayRequest cinetPayRequest);
}
