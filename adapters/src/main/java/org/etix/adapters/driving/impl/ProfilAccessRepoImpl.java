/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.adapters.driving.impl;



import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.etix.adapters.driving.repositories.FonctionnaliteRepository;
import org.etix.adapters.driving.repositories.ProfilAccessRepository;
import org.etix.adapters.driving.repositories.StrategieCompteRepository;
import org.etix.adapters.entities.Security.ProfilAccesEntity;
import org.etix.domain.exceptions.BadRequestException;
import org.etix.domain.exceptions.EntityAlreadyExistsException;
import org.etix.domain.exceptions.EntityNotExistsException;
import org.etix.domain.models.Security.Fonctionnalite;
import org.etix.domain.models.Security.ProfilAcces;
import org.etix.domain.models.Security.ProfilAccesSummary;
import org.etix.domain.models.Security.StrategieCompte;
import org.etix.domain.ports.driving.ProfilAccessRepo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProfilAccessRepoImpl implements ProfilAccessRepo {

    private final FonctionnaliteRepository fonctionnaliteService;
    private final StrategieCompteRepository strategieCompteService;
    private final ProfilAccessRepository profilRepository;


    public List<ProfilAcces> getProfilAcces() {
        return profilRepository.findAll().stream().map(ProfilAccesEntity::toDomain).toList();
    }

    @Override
    public List<ProfilAcces> getProfils() {
        return profilRepository.findAllProfilsWithFonctionnalites().stream().map(ProfilAccesEntity::toDomain).toList();
    }

    @Override
    public ProfilAcces createProfil(ProfilAcces profil) {

        Optional<ProfilAcces> optionalProfil = profilRepository.findByLibelleIgnoreCase(profil.getLibelle()).map(ProfilAccesEntity::toDomain);
        if (!optionalProfil.isEmpty()) {
            throw new EntityAlreadyExistsException(
                    String.format("Un profil existe avec le libelle %s", profil.getLibelle()));
        }

        profil.setCode(createCode(profil.getLibelle()));
        profil.setCreatedAt(LocalDateTime.now());
        return profilRepository.save(ProfilAccesEntity.toEntity(profil)).toDomain();
    }

    /**
     * @param val
     * @return
     */
    private String createCode(String val) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmSS"));
    }

    @Override
    public ProfilAcces updateProfil(ProfilAcces profil) {

        ProfilAcces toUpdate = getById(profil.getId());
        profil.setCode(toUpdate.getCode());
        profil.setCreatedAt(toUpdate.getCreatedAt());

        return profilRepository.save(ProfilAccesEntity.toEntity(profil)).toDomain();
    }


    @Transactional
    @Override
    public ProfilAcces saveProfil(ProfilAcces profilDto) {

        if (!isValidLibelle(profilDto.getLibelle())) {
            throw new BadRequestException("Le libelle est invalide");
        }

        if (profilDto.getStrategieCompte() == null) {
            throw new EntityNotExistsException("Aucune stratégie de compte trouvée pour ce profil d'accès.");
        }

        Fonctionnalite fonctionnaliteAccueil = null;
        if (profilDto.getFonctionnaliteAccueil() != null) {
            fonctionnaliteAccueil = fonctionnaliteService.getById(profilDto.getFonctionnaliteAccueil().getId()).toDomain();
        }


        StrategieCompte strategieCompte = strategieCompteService.getById(profilDto.getStrategieCompte().getId()).toDomain();
        profilDto.setFonctionnaliteAccueil(fonctionnaliteAccueil);
        profilDto.setStrategieCompte(strategieCompte);
        if (profilDto.getId() == null) {
            profilDto = createProfil(profilDto);
        } else {
            profilDto = updateProfil(profilDto);
        }
        return profilDto;
    }

    @Override
    public void deleteProfil(ProfilAcces profil) {
        ProfilAcces toUpdate = getById(profil.getId());
        profilRepository.delete(ProfilAccesEntity.toEntity(toUpdate));
    }

    @Override
    public ProfilAcces createProfilIfNotExists(String profilName, Set<Fonctionnalite> adminFeatures) {
        Optional<ProfilAcces> optionalRole = rechercherParName(profilName);
        if (optionalRole.isEmpty()) {
            ProfilAcces profil = new ProfilAcces();
            profil.setLibelle(profilName);
            profil.setFonctionnalites(adminFeatures);
            return profilRepository.save(ProfilAccesEntity.toEntity(profil)).toDomain();
        } else {
            return optionalRole.get();
        }
    }

    @Override
    public Optional<ProfilAcces> rechercherParName(String libelle) {
        return profilRepository.findByLibelle(libelle).map(ProfilAccesEntity::toDomain);
    }

    @Override
    public List<ProfilAcces> getActiveProfils() {
        return profilRepository.findByActive(true).stream().map(ProfilAccesEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public ProfilAcces getById(Integer id) {
        return profilRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(String.format("Role id %s est introuvable.", id))).toDomain();
    }

    @Override
    public ProfilAcces findProfilByLibelle(String libelle) {
        return profilRepository.findProfilByLibelle(libelle)
                .orElseThrow(() -> new EntityNotExistsException("Profil avec libelle " + libelle + " n'existe pas")).toDomain();
    }


    @Override
    public List<ProfilAcces> findByIdIn(Integer[] profilIds) {
        return profilRepository.findByIdIn(profilIds).stream().map(ProfilAccesEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ProfilAccesSummary> getActiveProfilsSummary() {
        return profilRepository.findAllProfilAccesSummaryDtoByStatutActif(Boolean.TRUE);
    }

    @Override
    public List<ProfilAccesSummary> getProfilAccesSummaryByUtilisateurId(Integer utilisateurId) {
        return profilRepository.findProfilAccesSummaryByUtilisateurId(utilisateurId).stream()
                .map(ProfilAccesEntity::toDomainSummary).collect(Collectors.toList());
    }

    private boolean isValidLibelle(String libelle) {
        String trimed = libelle.trim();
        return trimed.length() >= 5 && trimed.length() <= 25;
    }


}
