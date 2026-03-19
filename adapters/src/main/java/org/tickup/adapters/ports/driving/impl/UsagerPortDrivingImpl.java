package org.tickup.adapters.ports.driving.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tickup.adapters.entites.UsagerEntity;
import org.tickup.adapters.ports.driving.repositories.UsagerRepository;
import org.tickup.domain.models.Usager;
import org.tickup.domain.ports.driving.UsagerDriving;

@Component
public class UsagerPortDrivingImpl implements UsagerDriving {

    @Autowired
    private UsagerRepository usagerRepository;


    @Override
    public Usager saveUsager(Usager usager) {
        return usagerRepository.save(UsagerEntity.toEntity(usager)).toDomain();
    }

    @Override
    public Usager findByEmail(String email) {
        return usagerRepository.findByEmail(email).map(UsagerEntity::toDomain).orElse(null);
    }
}


