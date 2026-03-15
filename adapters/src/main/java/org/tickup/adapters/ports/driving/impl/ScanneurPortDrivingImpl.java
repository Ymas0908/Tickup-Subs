package org.tickup.adapters.ports.driving.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tickup.adapters.entites.ScanneurEntity;
import org.tickup.adapters.ports.driving.repositories.ScanneurRepository;
import org.tickup.adapters.ports.driving.repositories.UsagerRepository;
import org.tickup.domain.models.Scanneur;
import org.tickup.domain.ports.driving.ScanneurRepo;
@Component

public class ScanneurPortDrivingImpl implements ScanneurRepo {
    @Autowired
    private ScanneurRepository scanneurRepository;




    @Override
    public Scanneur save(Scanneur scanneur) {
        return 	scanneurRepository.save(ScanneurEntity.toEntity(scanneur)).toDomain();
    }
}
