package org.etix.adapters.driving.impl;



import lombok.RequiredArgsConstructor;
import org.etix.adapters.driving.repositories.StrategieCompteRepository;
import org.etix.adapters.entities.Security.StrategieCompteEntity;
import org.etix.domain.exceptions.BadRequestException;
import org.etix.domain.exceptions.EntityAlreadyExistsException;
import org.etix.domain.exceptions.EntityNotExistsException;
import org.etix.domain.models.Security.StrategieCompte;
import org.etix.domain.ports.driving.StrategieCompteRepo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class StrategieCompteRepoImpl implements StrategieCompteRepo {

    private final StrategieCompteRepository strategieCompteRepository;

    @Override
    public List<StrategieCompte> getStrategieComptes() {
        return strategieCompteRepository.findAll().stream().map(StrategieCompteEntity::toDomain).toList();
    }

    @Override
    public StrategieCompte saveStrategieCompte(StrategieCompte str) {

        if (str.getLongueurMinimalePassword() - (str.getNbreCaractereMajuscule() + str.getNbrCaractereSpeciaux() + str.getNbrChiffre()) < 0) {
            throw new BadRequestException("La longueur min doit être supérieure à la longueur des composantes.");
        }

        String reference = StrategieCompteEntity.toEntity(str).generateReference();
        String regex = StrategieCompteEntity.toEntity(str).generateRegex();
        str.setGeneratedRegex(regex);
        str.setReference(reference);
        if (null == str.getId()) {
            return createStrategieCompte(str);
        } else {
            return updateStrategieCompte(str);
        }
    }

    @Override
    public StrategieCompte createStrategieCompte(StrategieCompte strategieCompte) {

        if (strategieCompteRepository.countByLibelleIgnoreCase(strategieCompte.getLibelle()) > 0) {
            throw new EntityAlreadyExistsException("Une strategie de compte existe avec le même lebelle.");
        }

        Optional<StrategieCompte> optionalStrategie = findByReference(strategieCompte.getReference());
        if (!optionalStrategie.isEmpty()) {
            throw new EntityAlreadyExistsException("Une strategie de compte existe avec les même paramètres.");
        }
        strategieCompte.setCreatedAt(LocalDateTime.now());
        return strategieCompteRepository.save(StrategieCompteEntity.toEntity(strategieCompte)).toDomain();
    }

    @Override
    public StrategieCompte updateStrategieCompte(StrategieCompte strategieCompte) {

        StrategieCompte toUpdate = getById(strategieCompte.getId());
        if (!toUpdate.getLibelle().equals(strategieCompte.getLibelle()) && (strategieCompteRepository.countByLibelleIgnoreCase(strategieCompte.getLibelle()) > 0)) {
            throw new EntityAlreadyExistsException(String.format("Un Strategie de compte existe avec le libelle %s", strategieCompte.getLibelle()));
        }

        strategieCompte.setDateDernModification(LocalDateTime.now());
        strategieCompte.setCreatedAt(toUpdate.getCreatedAt());
        return strategieCompteRepository.save(StrategieCompteEntity.toEntity(strategieCompte)).toDomain();
    }

    @Override
    public void deleteStrategieCompte(StrategieCompte strategieCompte) {
        StrategieCompte toDelete = getById(strategieCompte.getId());
        if (DEFAULT_STRATEGIE_COMPTE.equalsIgnoreCase(toDelete.getLibelle())) {
            throw new BadRequestException("Vous n'etes pas autorisé à supprimer cette strategie de compte.");
        }
        strategieCompteRepository.delete(StrategieCompteEntity.toEntity(toDelete));
    }

    @Override
    public Optional<StrategieCompte> findById(Integer id) {
        return strategieCompteRepository.findById(id).map(StrategieCompteEntity::toDomain);
    }

    @Override
    public StrategieCompte getById(Integer id) {
        return findById(id).orElseThrow(() -> new EntityNotExistsException("Cette Strategie de compte  n'existe pas"));
    }


    @Override
    public Optional<StrategieCompte> findByLibelleIgnoreCase(String libelle) {
        return strategieCompteRepository.findByLibelleIgnoreCase(libelle).map(StrategieCompteEntity::toDomain);
    }

    private Optional<StrategieCompte> findByReference(String reference) {
        return strategieCompteRepository.findByReference(reference).map(StrategieCompteEntity::toDomain);
    }
}
