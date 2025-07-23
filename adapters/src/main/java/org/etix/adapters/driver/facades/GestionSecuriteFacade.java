package org.etix.adapters.driver.facades;



import lombok.AllArgsConstructor;
import org.etix.adapters.entities.Security.*;
import org.etix.domain.models.Security.ProfilAccesSummary;
import org.etix.domain.models.Security.UtilisateurSlim;
import org.etix.domain.models.enumerations.TypeLog;
import org.etix.domain.ports.driver.GererSecurite;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GestionSecuriteFacade {

    private final GererSecurite gererSecurite;

    public List<FonctionnaliteEntity> getFonctionnalites() {
        return gererSecurite.getFonctionnalites().stream().map(FonctionnaliteEntity::toEntity).toList();
    }


    public List<FonctionnaliteEntity> searchFeature(LocalDateTime dateA, LocalDateTime dateB) {
        return gererSecurite.searchFeature(dateA, dateB).stream().map(FonctionnaliteEntity::toEntity).toList();
    }


    public FonctionnaliteEntity saveFonctionnalite(FonctionnaliteEntity fonctionnalite) {
        return FonctionnaliteEntity.toEntity(gererSecurite.saveFonctionnalite(fonctionnalite.toDomain()));
    }


    public FonctionnaliteEntity createFonctionnalite(FonctionnaliteEntity fonctionnalite) {
        return FonctionnaliteEntity.toEntity(gererSecurite.createFonctionnalite(fonctionnalite.toDomain()));
    }


    public FonctionnaliteEntity updateFonctionnalite(FonctionnaliteEntity fonctionnalite) {
        return FonctionnaliteEntity.toEntity(gererSecurite.updateFonctionnalite(fonctionnalite.toDomain()));
    }


    public void deleteFonctionnalite(FonctionnaliteEntity fonctionnalite) {
        gererSecurite.deleteFonctionnalite(fonctionnalite.toDomain());
    }


    public FonctionnaliteEntity getFonctionaliteById(Integer Id) {
        return FonctionnaliteEntity.toEntity(gererSecurite.getFonctionaliteById(Id));
    }


    public Optional<FonctionnaliteEntity> findFonctionnaliteById(Integer id) {
        return gererSecurite.findFonctionnaliteById(id).map(FonctionnaliteEntity::toEntity);
    }


    public FonctionnaliteEntity rechercherParLibelle(String libelle) {
        return FonctionnaliteEntity.toEntity(gererSecurite.rechercherParLibelle(libelle));
    }


    public List<FonctionnaliteEntity> getFonctionnalitesByProfilIdsIn(Set<Integer> profilsId) {
        return gererSecurite.getFonctionnalitesByProfilIdsIn(profilsId).stream().map(FonctionnaliteEntity::toEntity).toList();
    }


    public List<LogAccesEntity> getLogAccess() {
        return gererSecurite.getLogAccess().stream().map(LogAccesEntity::toEntity).toList();
    }


    public LogAccesEntity createLogAcces(LogAccesEntity logAcces) {
        return LogAccesEntity.toEntity(gererSecurite.createLogAcces(logAcces.toDomain()));
    }


    public void deleteLogAcces(LogAccesEntity logAcces) {
        gererSecurite.deleteLogAcces(logAcces.toDomain());
    }


    public List<LogAccesEntity> getLogAccessDtoByDateBetweenAndTypeLog(LocalDateTime dateA, LocalDateTime dateB, TypeLog typeLog) {
        return gererSecurite.getLogAccessDtoByDateBetweenAndTypeLog(dateA, dateB, typeLog).stream().map(LogAccesEntity::toEntity).toList();
    }


    public ProfilAccesEntity getProfilAccesById(Integer id) {
        return ProfilAccesEntity.toEntity(gererSecurite.getProfilAccesById(id));
    }


    public List<ProfilAccesEntity> getProfils() {
        return gererSecurite.getProfils().stream().map(ProfilAccesEntity::toEntity).toList();
    }


    public ProfilAccesEntity saveProfil(ProfilAccesEntity profil) {
        return ProfilAccesEntity.toEntity(gererSecurite.saveProfil(profil.toDomain()));
    }


    public ProfilAccesEntity createProfil(ProfilAccesEntity profil) {
        return ProfilAccesEntity.toEntity(gererSecurite.createProfil(profil.toDomain()));
    }


    public ProfilAccesEntity updateProfil(ProfilAccesEntity profil) {
        return ProfilAccesEntity.toEntity(gererSecurite.updateProfil(profil.toDomain()));
    }


    public void deleteProfil(ProfilAccesEntity profil) {
        gererSecurite.deleteProfil(profil.toDomain());
    }


    public ProfilAccesEntity createProfilIfNotExists(String roleName, Set<FonctionnaliteEntity> adminFeatures) {
        return ProfilAccesEntity.toEntity(gererSecurite.createProfilIfNotExists(roleName, adminFeatures.stream().map(FonctionnaliteEntity::toDomain).collect(Collectors.toSet())));
    }


    public Optional<ProfilAccesEntity> rechercherParName(String name) {
        return gererSecurite.rechercherParName(name).map(ProfilAccesEntity::toEntity);
    }


    public List<ProfilAccesEntity> getActiveProfils() {
        return gererSecurite.getActiveProfils().stream().map(ProfilAccesEntity::toEntity).toList();
    }


    public ProfilAccesEntity findProfilByLibelle(String libelle) {
        return ProfilAccesEntity.toEntity(gererSecurite.findProfilByLibelle(libelle));
    }


    public List<ProfilAccesSummary> getActiveProfilsSummary() {
        return gererSecurite.getActiveProfilsSummary();
    }


    public List<ProfilAccesEntity> findByIdIn(Integer[] profilIds) {
        return gererSecurite.findByIdIn(profilIds).stream().map(ProfilAccesEntity::toEntity).toList();
    }


    public List<ProfilAccesSummary> getProfilAccesSummaryByUtilisateurId(Integer utilisateurId) {
        return gererSecurite.getProfilAccesSummaryByUtilisateurId(utilisateurId);
    }


    public List<StrategieCompteEntity> getStrategieComptes() {
        return gererSecurite.getStrategieComptes().stream().map(StrategieCompteEntity::toEntity).toList();
    }


    public StrategieCompteEntity saveStrategieCompte(StrategieCompteEntity strategieCompte) {
        return StrategieCompteEntity.toEntity(gererSecurite.saveStrategieCompte(strategieCompte.toDomain()));
    }


    public StrategieCompteEntity createStrategieCompte(StrategieCompteEntity strategieCompte) {
        return StrategieCompteEntity.toEntity(gererSecurite.createStrategieCompte(strategieCompte.toDomain()));
    }


    public StrategieCompteEntity updateStrategieCompte(StrategieCompteEntity strategieCompte) {
        return StrategieCompteEntity.toEntity(gererSecurite.updateStrategieCompte(strategieCompte.toDomain()));
    }


    public void deleteStrategieCompte(StrategieCompteEntity strategieCompte) {
        gererSecurite.deleteStrategieCompte(strategieCompte.toDomain());
    }


    public Optional<StrategieCompteEntity> findStrategieCompteById(Integer id) {
        return gererSecurite.findStrategieCompteById(id).map(StrategieCompteEntity::toEntity);
    }


    public StrategieCompteEntity getStrategieCompteById(Integer id) {
        return StrategieCompteEntity.toEntity(gererSecurite.getStrategieCompteById(id));
    }


    public Optional<StrategieCompteEntity> findByLibelleIgnoreCase(String libelle) {
        return gererSecurite.findByLibelleIgnoreCase(libelle).map(StrategieCompteEntity::toEntity);
    }


    public List<UtilisateurEntity> getUtilisateurs() {
        return gererSecurite.getUtilisateurs().stream().map(UtilisateurEntity::toEntity).toList();
    }


    public List<UtilisateurSlim> getUtilisateursDto() {
        return gererSecurite.getUtilisateursDto();
    }


    public List<UtilisateurEntity> getAllUtilisateurs() {
        return gererSecurite.getAllUtilisateursDto().stream().map(UtilisateurEntity::toEntity).toList();
    }


    public UtilisateurEntity saveUtilisateur(UtilisateurEntity utilisateur) {
        return UtilisateurEntity.toEntity(gererSecurite.saveUtilisateur(utilisateur.toDomain()));
    }


    public UtilisateurEntity reinitialiseMotPasse(UtilisateurEntity utilisateur) {
        return UtilisateurEntity.toEntity(gererSecurite.reinitialiseMotPasse(utilisateur.toDomain()));
    }


    public UtilisateurEntity createUtilisateur(UtilisateurEntity utilisateur) {
        return UtilisateurEntity.toEntity(gererSecurite.createUtilisateur(utilisateur.toDomain()));
    }


    public UtilisateurEntity updateUtilisateur(UtilisateurEntity utilisateur) {
        return UtilisateurEntity.toEntity(gererSecurite.updateUtilisateur(utilisateur.toDomain()));
    }


    public UtilisateurEntity deleteUtilisateur(UtilisateurEntity utilisateur) {
        return UtilisateurEntity.toEntity(gererSecurite.deleteUtilisateur(utilisateur.toDomain()));
    }


    public UtilisateurEntity getUtilisateurById(Integer id) {
        return UtilisateurEntity.toEntity(gererSecurite.getUtilisateurById(id));
    }


    public UtilisateurEntity updateProfilAccess(UtilisateurEntity utilisateur, Integer[] profilSelectedId) {
        return UtilisateurEntity.toEntity(gererSecurite.updateProfilAccess(utilisateur.toDomain(), profilSelectedId));
    }


    public List<UtilisateurSlim> utilisateuersSlimDtoSelonProfil(ProfilAccesEntity profilAcces) {
        return gererSecurite.utilisateuersSlimDtoSelonProfil(profilAcces.toDomain());
    }


    public UtilisateurEntity rechercherUtilisateurParLoginEtPassword(String login, String password) {
        return UtilisateurEntity.toEntity(gererSecurite.getUtilisateurByLoginAndPassword(login, password));
    }

    public UtilisateurEntity getUtilisateurConnected() {
        return UtilisateurEntity.toEntity(gererSecurite.getUtilisateurConnected());
    }

    public void traceLog(TypeLog typeLog, String libelle, String description, String auteur) {
        LogAccesEntity logAcces = new LogAccesEntity();
        logAcces.setType(typeLog);
        logAcces.setLibelle(libelle);
        logAcces.setDescription(description);
        logAcces.setAuteur(auteur);
        this.createLogAcces(logAcces);
    }

}
