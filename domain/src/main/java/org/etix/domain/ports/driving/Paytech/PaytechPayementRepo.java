package org.etix.domain.ports.driving.Paytech;

import org.etix.domain.request.PaytechPaymentRequest;

public interface PaytechPayementRepo {

    void initierPaiement(PaytechPaymentRequest paytechPaymentRequest);

}
