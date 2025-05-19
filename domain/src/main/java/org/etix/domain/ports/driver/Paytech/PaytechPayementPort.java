package org.etix.domain.ports.driver.Paytech;


import org.etix.domain.request.PaytechPaymentRequest;

public interface PaytechPayementPort {

    void initierPaiement(PaytechPaymentRequest paytechPaymentRequest);
}
