package org.etix.domain.services;

import org.etix.domain.ddd.DomaineService;
import org.etix.domain.ports.driver.Paytech.PaytechPayementPort;
import org.etix.domain.ports.driving.Paytech.PaytechPayementRepo;
import org.etix.domain.request.PaytechPaymentRequest;

@DomaineService
public class PaytechPayementService implements PaytechPayementPort {

    private final PaytechPayementRepo paytechPayementRepo;

    public PaytechPayementService(PaytechPayementRepo paytechPayementRepo) {
        this.paytechPayementRepo = paytechPayementRepo;
    }


    @Override
    public void initierPaiement(PaytechPaymentRequest paytechPaymentRequest) {
        paytechPayementRepo.initierPaiement(paytechPaymentRequest);

    }


}
