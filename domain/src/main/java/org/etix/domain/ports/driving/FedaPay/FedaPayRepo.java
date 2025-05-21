package org.etix.domain.ports.driving.FedaPay;

import org.etix.domain.request.FedaPayRequest;

public interface FedaPayRepo {

    void initierPaiement(FedaPayRequest fedaPayRequest);
}
