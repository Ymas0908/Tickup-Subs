package org.tickup.adapters.ports.driving.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tickup.adapters.entites.ScanneurEntity;

import java.util.Optional;

public interface ScanneurRepository extends JpaRepository<ScanneurEntity, Integer> {
    Optional<ScanneurEntity> findByRefScanneur(String refScanneur);

}
