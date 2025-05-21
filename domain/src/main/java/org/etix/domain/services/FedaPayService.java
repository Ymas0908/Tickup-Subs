package org.etix.domain.services;

import org.etix.domain.ddd.DomaineService;
import org.etix.domain.ports.driver.FedaPay.FedaPayPort;
import org.etix.domain.ports.driving.FedaPay.FedaPayRepo;
import org.etix.domain.request.FedaPayRequest;

@DomaineService
public class FedaPayService implements FedaPayPort {

    private final FedaPayRepo fedaPayRepo;

    public FedaPayService(FedaPayRepo fedaPayRepo) {
        this.fedaPayRepo = fedaPayRepo;
    }

    @Override
    public void initierPaiement(FedaPayRequest fedaPayRequest) {
        fedaPayRepo.initierPaiement(fedaPayRequest);

    }
}
