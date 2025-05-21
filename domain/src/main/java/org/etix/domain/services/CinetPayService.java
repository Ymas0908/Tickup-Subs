//package org.etix.domain.services;
//
//import org.etix.domain.ddd.DomaineService;
//import org.etix.domain.ports.driver.CinetPay.CinetPayPort;
//import org.etix.domain.ports.driving.CineyPay.CinetPayRepo;
//import org.etix.domain.request.CinetPayRequest;
//
//@DomaineService
//public class CinetPayService implements CinetPayPort {
//    private final CinetPayRepo cinetPayRepo;
//
//    public CinetPayService(CinetPayRepo cinetPayRepo) {
//        this.cinetPayRepo = cinetPayRepo;
//    }
//
//
//    @Override
//    public void initierPaiement(CinetPayRequest cinetPayRequest) {
//        cinetPayRepo.initierPaiement(cinetPayRequest);
//
//    }
//}
