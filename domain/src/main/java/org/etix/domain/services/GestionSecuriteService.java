package org.etix.domain.services;


import org.etix.domain.ddd.DomaineService;
import org.etix.domain.models.Security.*;
import org.etix.domain.models.enumerations.TypeLog;
import org.etix.domain.ports.driver.GererSecurite;
import org.etix.domain.ports.driving.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DomaineService
public class GestionSecuriteService implements GererSecurite {
    private final LogAccesRepo logAccesRepo;
    private final ProfilAccessRepo profilAccesRepo;
    private final UtilisateurRepo utilisateurRepo;
    private final FonctionnaliteRepo fonctionnaliteRepo;

    private final StrategieCompteRepo strategieCompteRepo;

    public GestionSecuriteService(LogAccesRepo logAccesRepo, ProfilAccessRepo profilAccesRepo, UtilisateurRepo utilisateurRepo, FonctionnaliteRepo fonctionnaliteRepo, StrategieCompteRepo strategieCompteRepo) {
        this.logAccesRepo = logAccesRepo;
        this.profilAccesRepo = profilAccesRepo;
        this.utilisateurRepo = utilisateurRepo;
        this.fonctionnaliteRepo = fonctionnaliteRepo;

        this.strategieCompteRepo = strategieCompteRepo;
    }

    @Override
    public List<Fonctionnalite> getFonctionnalites() {
        return fonctionnaliteRepo.getFonctionnalites();
    }

    @Override
    public List<Fonctionnalite> searchFeature(LocalDateTime dateA, LocalDateTime dateB) {
        return fonctionnaliteRepo.searchFeature(dateA, dateB);
    }

    @Override
    public Fonctionnalite saveFonctionnalite(Fonctionnalite fonctionnalite) {
        return fonctionnaliteRepo.saveFonctionnalite(fonctionnalite);
    }

    @Override
    public Fonctionnalite createFonctionnalite(Fonctionnalite fonctionnalite) {
        return fonctionnaliteRepo.createFonctionnalite(fonctionnalite);
    }

    @Override
    public Fonctionnalite updateFonctionnalite(Fonctionnalite fonctionnalite) {
        return fonctionnaliteRepo.updateFonctionnalite(fonctionnalite);
    }

    @Override
    public void deleteFonctionnalite(Fonctionnalite fonctionnalite) {
        fonctionnaliteRepo.deleteFonctionnalite(fonctionnalite);
    }

    @Override
    public Fonctionnalite getFonctionaliteById(Integer Id) {
        return fonctionnaliteRepo.getById(Id);
    }

    @Override
    public Optional<Fonctionnalite> findFonctionnaliteById(Integer id) {
        return fonctionnaliteRepo.findById(id);
    }

    @Override
    public Fonctionnalite rechercherParLibelle(String libelle) {
        return fonctionnaliteRepo.rechercherParLibelle(libelle);
    }

    @Override
    public List<Fonctionnalite> getFonctionnalitesByProfilIdsIn(Set<Integer> profilsId) {
        return fonctionnaliteRepo.getFonctionnalitesByProfilIdsIn(profilsId);
    }


    @Override
    public List<LogAcces> getLogAccess() {
        return logAccesRepo.getLogAccess();
    }

    @Override
    public LogAcces createLogAcces(LogAcces logAcces) {
        return logAccesRepo.createLogAcces(logAcces);
    }

    @Override
    public void deleteLogAcces(LogAcces logAcces) {
        logAccesRepo.deleteLogAcces(logAcces);
    }

    @Override
    public List<LogAcces> getLogAccessDtoByDateBetweenAndTypeLog(LocalDateTime dateA, LocalDateTime dateB, TypeLog typeLog) {
        return logAccesRepo.getLogAccessDtoByDateBetweenAndTypeLog(dateA, dateB, typeLog);
    }

    @Override
    public ProfilAcces getProfilAccesById(Integer id) {
        return profilAccesRepo.getById(id);
    }

    @Override
    public List<ProfilAcces> getProfils() {
        return profilAccesRepo.getProfils();
    }

    @Override
    public ProfilAcces saveProfil(ProfilAcces profil) {
        return profilAccesRepo.saveProfil(profil);
    }

    @Override
    public ProfilAcces createProfil(ProfilAcces profil) {
        return profilAccesRepo.createProfil(profil);
    }

    @Override
    public ProfilAcces updateProfil(ProfilAcces profil) {
        return profilAccesRepo.updateProfil(profil);
    }

    @Override
    public void deleteProfil(ProfilAcces profil) {
        profilAccesRepo.deleteProfil(profil);
    }

    @Override
    public ProfilAcces createProfilIfNotExists(String roleName, Set<Fonctionnalite> adminFeatures) {
        return profilAccesRepo.createProfilIfNotExists(roleName, adminFeatures);
    }

    @Override
    public Optional<ProfilAcces> rechercherParName(String name) {
        return profilAccesRepo.rechercherParName(name);
    }

    @Override
    public List<ProfilAcces> getActiveProfils() {
        return profilAccesRepo.getActiveProfils();
    }

    @Override
    public ProfilAcces findProfilByLibelle(String libelle) {
        return profilAccesRepo.findProfilByLibelle(libelle);
    }

    @Override
    public List<ProfilAccesSummary> getActiveProfilsSummary() {
        return profilAccesRepo.getActiveProfilsSummary();
    }

    @Override
    public List<ProfilAcces> findByIdIn(Integer[] profilIds) {
        return profilAccesRepo.findByIdIn(profilIds);
    }

    @Override
    public List<ProfilAccesSummary> getProfilAccesSummaryByUtilisateurId(Integer utilisateurId) {
        return profilAccesRepo.getProfilAccesSummaryByUtilisateurId(utilisateurId);
    }

    @Override
    public List<StrategieCompte> getStrategieComptes() {
        return strategieCompteRepo.getStrategieComptes();
    }

    @Override
    public StrategieCompte saveStrategieCompte(StrategieCompte strategieCompte) {
        return strategieCompteRepo.saveStrategieCompte(strategieCompte);
    }

    @Override
    public StrategieCompte createStrategieCompte(StrategieCompte strategieCompte) {
        return strategieCompteRepo.createStrategieCompte(strategieCompte);
    }

    @Override
    public StrategieCompte updateStrategieCompte(StrategieCompte strategieCompte) {
        return strategieCompteRepo.updateStrategieCompte(strategieCompte);
    }

    @Override
    public void deleteStrategieCompte(StrategieCompte strategieCompte) {
        strategieCompteRepo.deleteStrategieCompte(strategieCompte);
    }

    @Override
    public Optional<StrategieCompte> findStrategieCompteById(Integer id) {
        return strategieCompteRepo.findById(id);
    }

    @Override
    public StrategieCompte getStrategieCompteById(Integer id) {
        return strategieCompteRepo.getById(id);
    }

    @Override
    public Optional<StrategieCompte> findByLibelleIgnoreCase(String libelle) {
        return strategieCompteRepo.findByLibelleIgnoreCase(libelle);
    }


    @Override
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepo.getUtilisateurs();
    }

    @Override
    public List<UtilisateurSlim> getUtilisateursDto() {
        return utilisateurRepo.getUtilisateursDto();
    }

    @Override
    public List<Utilisateur> getAllUtilisateursDto() {
        return utilisateurRepo.getAllUtilisateursDto();
    }

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepo.saveUtilisateur(utilisateur);
    }

    @Override
    public Utilisateur reinitialiseMotPasse(Utilisateur utilisateur) {
        return utilisateurRepo.reinitialiseMotPasse(utilisateur);
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepo.createUtilisateur(utilisateur);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepo.updateUtilisateur(utilisateur);
    }

    @Override
    public Utilisateur deleteUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepo.deleteUtilisateur(utilisateur);
    }

    @Override
    public Utilisateur getUtilisateurById(Integer valueOf) {
        return utilisateurRepo.getById(valueOf);
    }


    @Override
    public Utilisateur updateProfilAccess(Utilisateur utilisateur, Integer[] profilSelectedId) {
        return utilisateurRepo.updateProfilAccess(utilisateur, profilSelectedId);
    }


    @Override
    public List<UtilisateurSlim> utilisateuersSlimDtoSelonProfil(ProfilAcces profilAcces) {
        return utilisateurRepo.utilisateuersSlimDtoSelonProfil(profilAcces);
    }

    @Override
    public Optional<Utilisateur> findUtilisateurByLogin(String login) {
        return utilisateurRepo.findByLogin(login);
    }


    @Override
    public Utilisateur getUtilisateurByLoginAndPassword(String login, String password) {
        return utilisateurRepo.getUtilisateurByLoginAndPassword(login, password);
    }

    @Override
    public Utilisateur getUtilisateurConnected() {
        return utilisateurRepo.getUtilisateurConnected();
    }


}
