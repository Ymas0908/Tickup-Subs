package org.etix.domain.services;

import org.etix.domain.ddd.DomaineService;
import org.etix.domain.ports.driver.MagmaOnePay.MagmaOnePayPort;
import org.etix.domain.ports.driving.MagmaOnePay.MagmaOnePayRepo;
import org.etix.domain.request.MagmaOnePayRequest;

@DomaineService
public class MagmaOneService implements MagmaOnePayPort {
    private final MagmaOnePayRepo magmaOnePayRepo;

    public MagmaOneService(MagmaOnePayRepo magmaOnePayRepo) {
        this.magmaOnePayRepo = magmaOnePayRepo;
    }


    @Override
    public void effectuerPaiement(MagmaOnePayRequest magmaOnePayRequest) {
        magmaOnePayRepo.effectuerPaiement(magmaOnePayRequest);

    }
}
