package org.tickup.adapters.ports.driving.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tickup.adapters.entites.UsagerEntity;

import java.util.Optional;

public interface UsagerRepositoryRepo extends JpaRepository<UsagerEntity, Integer> {
    Optional<UsagerEntity> findByRefUsager(String refUsager);

}
