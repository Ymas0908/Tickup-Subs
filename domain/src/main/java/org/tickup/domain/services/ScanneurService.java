package org.tickup.domain.services;

import org.tickup.domain.ddd.DomaineService;
import org.tickup.domain.exceptions.BadRequestException;
import org.tickup.domain.models.Scanneur;
import org.tickup.domain.models.Usager;
import org.tickup.domain.ports.driver.ScanneurPort;
import org.tickup.domain.ports.driving.ScanneurRepo;
import org.tickup.domain.requests.ScanneursRequest;
import org.tickup.domain.requests.UsagersRequest;
import org.tickup.domain.utils.AppUtils;
@DomaineService
public class ScanneurService implements ScanneurPort {
    
    private final ScanneurRepo scanneurRepo;

    public ScanneurService(ScanneurRepo scanneurRepo) {
        this.scanneurRepo = scanneurRepo;
    }

    private static void validateScanneur(ScanneursRequest scanneursRequest) {
        if (scanneursRequest.getNom() == null || scanneursRequest.getNom().isEmpty()) {
            throw new BadRequestException("scanneur's name is required");
        }
        if ((scanneursRequest.getTelephone() == null)) {
            throw new BadRequestException("N° tel is required");
        }
        if (scanneursRequest.getDateNaissance()== null) {
            throw new BadRequestException("Date naissance scanneur  is required");
        }
        if (scanneursRequest.getPrenom() == null || scanneursRequest.getPrenom().isEmpty()) {
            throw new BadRequestException("scanneur's surname is required");
        }

        if (scanneursRequest.getEmail() == null || scanneursRequest.getEmail().isEmpty()) {
            throw new BadRequestException("scanneur's address email is required");
        }


    }

    private static Scanneur buildScanneurToSave(ScanneursRequest scanneursRequest) {

        return new Scanneur.Builder()
                .refScanneur(AppUtils.generateReference("SCAN-"))
                .nom(scanneursRequest.getNom())
                .prenom(scanneursRequest.getPrenom())
                .telephone(scanneursRequest.getTelephone())
                .email(scanneursRequest.getEmail())
                .dateNaissance(scanneursRequest.getDateNaissance())
                .build();
    }

    @Override
    public String saveScanneur(ScanneursRequest scanneursRequest) {
        System.out.println("saveUsager UsagerRequest  :::: " + scanneursRequest);
        validateScanneur(scanneursRequest);
        Scanneur usagerToSave = buildScanneurToSave(scanneursRequest);
        Scanneur savedScanneur= scanneurRepo.save(usagerToSave);
        return savedScanneur.getRefScanneur();
    }
}
