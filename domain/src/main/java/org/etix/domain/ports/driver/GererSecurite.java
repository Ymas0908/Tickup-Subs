package org.etix.domain.ports.driver;



import org.etix.domain.models.Security.*;
import org.etix.domain.models.enumerations.TypeLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GererSecurite {

    public final static String DEFAULT_STRATEGIE_COMPTE = "DEFAULT";

    List<Fonctionnalite> getFonctionnalites();

    List<Fonctionnalite> searchFeature(LocalDateTime dateA, LocalDateTime dateB);

    Fonctionnalite saveFonctionnalite(Fonctionnalite fonctionnalite);

    Fonctionnalite createFonctionnalite(Fonctionnalite fonctionnalite);

    Fonctionnalite updateFonctionnalite(Fonctionnalite fonctionnalite);

    void deleteFonctionnalite(Fonctionnalite fonctionnalite);

    Fonctionnalite getFonctionaliteById(Integer Id);

    Optional<Fonctionnalite> findFonctionnaliteById(Integer id);

    Fonctionnalite rechercherParLibelle(String libelle);

    List<Fonctionnalite> getFonctionnalitesByProfilIdsIn(Set<Integer> profilsId);


    List<LogAcces> getLogAccess();

    LogAcces createLogAcces(LogAcces logAcces);

    void deleteLogAcces(LogAcces logAcces);

    List<LogAcces> getLogAccessDtoByDateBetweenAndTypeLog(LocalDateTime dateA, LocalDateTime dateB, TypeLog typeLog);

    ProfilAcces getProfilAccesById(Integer id);

    List<ProfilAcces> getProfils();

    ProfilAcces saveProfil(ProfilAcces profil);

    ProfilAcces createProfil(ProfilAcces profil);

    ProfilAcces updateProfil(ProfilAcces profil);

    void deleteProfil(ProfilAcces profil);

    ProfilAcces createProfilIfNotExists(String roleName, Set<Fonctionnalite> adminFeatures);

    Optional<ProfilAcces> rechercherParName(String name);

    List<ProfilAcces> getActiveProfils();

    ProfilAcces findProfilByLibelle(String libelle);

    List<ProfilAccesSummary> getActiveProfilsSummary();

    List<ProfilAcces> findByIdIn(Integer[] profilIds);

    List<ProfilAccesSummary> getProfilAccesSummaryByUtilisateurId(Integer utilisateurId);

    List<StrategieCompte> getStrategieComptes();

    StrategieCompte saveStrategieCompte(StrategieCompte strategieCompte);

    StrategieCompte createStrategieCompte(StrategieCompte strategieCompte);

    StrategieCompte updateStrategieCompte(StrategieCompte strategieCompte);

    void deleteStrategieCompte(StrategieCompte strategieCompte);

    Optional<StrategieCompte> findStrategieCompteById(Integer id);

    StrategieCompte getStrategieCompteById(Integer id);

    Optional<StrategieCompte> findByLibelleIgnoreCase(String libelle);


    List<Utilisateur> getUtilisateurs();

    List<UtilisateurSlim> getUtilisateursDto();

    List<Utilisateur> getAllUtilisateursDto();

    Utilisateur saveUtilisateur(Utilisateur utilisateur);

    Utilisateur reinitialiseMotPasse(Utilisateur utilisateur);

    Utilisateur createUtilisateur(Utilisateur utilisateur);

    Utilisateur updateUtilisateur(Utilisateur utilisateur);

    Utilisateur deleteUtilisateur(Utilisateur utilisateur);

    Utilisateur getUtilisateurById(Integer valueOf);


    Utilisateur updateProfilAccess(Utilisateur utilisateur, Integer[] profilSelectedId);
    

    List<UtilisateurSlim> utilisateuersSlimDtoSelonProfil(ProfilAcces profilAcces);

    Optional<Utilisateur> findUtilisateurByLogin(String login);


    Utilisateur getUtilisateurByLoginAndPassword(String login, String password);

    Utilisateur getUtilisateurConnected();
}
