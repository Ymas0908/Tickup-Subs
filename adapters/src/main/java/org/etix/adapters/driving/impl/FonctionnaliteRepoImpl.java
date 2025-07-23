package org.etix.adapters.driving.impl;



import lombok.AllArgsConstructor;
import org.etix.adapters.driving.repositories.FonctionnaliteRepository;
import org.etix.adapters.entities.Security.FonctionnaliteEntity;
import org.etix.domain.exceptions.BadRequestException;
import org.etix.domain.exceptions.EntityAlreadyExistsException;
import org.etix.domain.exceptions.EntityNotExistsException;
import org.etix.domain.models.Security.Fonctionnalite;
import org.etix.domain.ports.driving.FonctionnaliteRepo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ITCENTREX
 */
@AllArgsConstructor
@Component
public class FonctionnaliteRepoImpl implements FonctionnaliteRepo {

    private final FonctionnaliteRepository fonctionnaliteRepository;

    @Override
    public List<Fonctionnalite> getFonctionnalites() {
        return fonctionnaliteRepository.findAll().stream().map(FonctionnaliteEntity::toDomain).collect(Collectors.toList());
    }


    @Override
    public Fonctionnalite createFonctionnalite(Fonctionnalite fonctionnalite) {

        if (null == fonctionnalite.getPageUrl()) {
            throw new BadRequestException("L'url de la page n'est invalide");
        }

        List<Fonctionnalite> fonctionnalitesFound = fonctionnaliteRepository
                .findAllByCodeOrLibelleIgnoreCase(fonctionnalite.getCode(), fonctionnalite.getLibelle()).stream().map(FonctionnaliteEntity::toDomain).collect(Collectors.toList());
        if (!fonctionnalitesFound.isEmpty()) {
            throw new EntityAlreadyExistsException("Une fonctionnalité existe avec ce code ou ce libelle");
        }
        fonctionnalite.setCreatedAt(LocalDateTime.now());
        return fonctionnaliteRepository.save(FonctionnaliteEntity.toEntity(fonctionnalite)).toDomain();
    }

    @Override
    public Fonctionnalite updateFonctionnalite(Fonctionnalite fonctionnalite) {
        Fonctionnalite toUpdate = getById(fonctionnalite.getId());
        fonctionnalite.setPageUrl(toUpdate.getPageUrl());
        fonctionnalite.setCreatedAt(toUpdate.getCreatedAt());
        return fonctionnaliteRepository.save(FonctionnaliteEntity.toEntity(fonctionnalite)).toDomain();
    }

    @Override
    public void deleteFonctionnalite(Fonctionnalite fonctionnalite) {
        Fonctionnalite toDelete = getById(fonctionnalite.getId());
        fonctionnaliteRepository.delete(FonctionnaliteEntity.toEntity(toDelete));
    }


    @Override
    public Optional<Fonctionnalite> findById(Integer id) {
        return fonctionnaliteRepository.findById(id).map(FonctionnaliteEntity::toDomain);
    }

    @Override
    public List<Fonctionnalite> searchFeature(LocalDateTime dateA, LocalDateTime dateB) {
        return fonctionnaliteRepository.findByCreatedAtBetween(dateA, dateB).stream().map(FonctionnaliteEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Fonctionnalite saveFonctionnalite(Fonctionnalite fonctionnalite) {

        if (!isValidLibelle(fonctionnalite.getLibelle())) {
            throw new BadRequestException("Le libellé est invalide");
        }

        if (!isValidCode(fonctionnalite.getCode())) {
            throw new BadRequestException("Le format du code est invalide");
        }

        if (fonctionnalite.getId() == null) {
            return createFonctionnalite(fonctionnalite);
        } else {
            return updateFonctionnalite(fonctionnalite);
        }
    }


    @Override
    public List<Fonctionnalite> getFonctionnalitesByProfilIdsIn(Set<Integer> profilsId) {
        return fonctionnaliteRepository.findFonctionnalitesByProfilIdsIn(profilsId).stream().map(FonctionnaliteEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Fonctionnalite getById(Integer id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotExistsException(String.format("Fonctionnalité id %s est introuvable.", id)));
    }

    @Override
    public Fonctionnalite rechercherParLibelle(String libelle) {
        return fonctionnaliteRepository.findByLibelleIgnoreCase(libelle).orElseThrow(() -> new EntityNotExistsException(
                String.format("Fonctionnalité avec libellé %s est introuvable.", libelle))).toDomain();
    }

    private boolean isValidLibelle(String libelle) {
        return libelle != null && libelle.trim().length() > 4 && libelle.trim().length() < 120;
    }

    private boolean isValidCode(String code) {
        return code != null && code.trim().length() == 8 && code.indexOf("_") == 4;
    }


}