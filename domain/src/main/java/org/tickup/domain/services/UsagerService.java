package org.tickup.domain.services;

import org.tickup.domain.ddd.DomaineService;
import org.tickup.domain.exceptions.BadRequestException;
import org.tickup.domain.models.Usager;
import org.tickup.domain.ports.driving.UsagerDriving;
import org.tickup.domain.ports.driver.UsagerPortDriver;
import org.tickup.domain.requests.UsagersRequest;
import org.tickup.domain.utils.AppUtils;

@DomaineService

public class UsagerService implements UsagerPortDriver {
    private final UsagerDriving usagerPortDriving;



    public UsagerService(UsagerDriving usagerPortDriving) {
        this.usagerPortDriving = usagerPortDriving;
    }

    private static void validateUsager(UsagersRequest usagerRequest) {
        if (usagerRequest.getNom() == null || usagerRequest.getNom().isEmpty()) {
            throw new BadRequestException("Usager's name is required");
        }
        if ((usagerRequest.getTelephone() == null)) {
            throw new BadRequestException("N° tel is required");
        }
        if (usagerRequest.getDateNaissance()== null) {
            throw new BadRequestException("Date naissance usager  is required");
        }
        if (usagerRequest.getPrenom() == null || usagerRequest.getPrenom().isEmpty()) {
            throw new BadRequestException("Usager's surname is required");
        }

        if (usagerRequest.getEmail() == null || usagerRequest.getEmail().isEmpty()) {
            throw new BadRequestException("Usager's address email is required");
        }


    }

    private static Usager buildUsagerToSave(UsagersRequest usagerRequest) {

        return new Usager.Builder()
                .refUsager(AppUtils.generateReference("USER-"))
                .nom(usagerRequest.getNom())
                .prenom(usagerRequest.getPrenom())
                .telephone(usagerRequest.getTelephone())
                .email(usagerRequest.getEmail())
                .dateNaissance(usagerRequest.getDateNaissance())
                .build();
    }



    @Override
    public String saveUsager(UsagersRequest usagerRequest) {
        System.out.println("saveUsager UsagerRequest  :::: " + usagerRequest);
        validateUsager(usagerRequest);
        Usager usagerToSave = buildUsagerToSave(usagerRequest);
        Usager savedUsager = usagerPortDriving.saveUsager(usagerToSave);
        return savedUsager.getRefUsager();
    }

}
