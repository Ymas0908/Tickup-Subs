package org.etix.domain.services;

import org.etix.domain.ddd.DomaineService;
import org.etix.domain.ports.driver.NotchPayPort;
import org.etix.domain.ports.driving.NotchPayRepo;
import org.etix.domain.request.NotchPayRequest;

@DomaineService
public class NotchPayService implements NotchPayPort {

    private final NotchPayRepo notchPayRepo;

    public NotchPayService(NotchPayRepo notchPayRepo) {
        this.notchPayRepo = notchPayRepo;
    }

    @Override
    public void initierPaiement(NotchPayRequest notchPayRequest) {
        notchPayRepo.initierPaiement(notchPayRequest);
    }
}
