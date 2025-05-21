package org.etix.domain.ports.driver.FedaPay;

import org.etix.domain.request.FedaPayRequest;

public interface FedaPayPort {

    void initierPaiement(FedaPayRequest fedaPayRequest);
}
