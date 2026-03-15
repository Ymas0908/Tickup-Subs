package org.tickup.domain.ports.driving;

import org.tickup.domain.models.Scanneur;
import org.tickup.domain.models.UtilisateurScanneur;

import java.util.List;
import java.util.Optional;

public interface GererUtilisateurScanneurPortDriver {
    public final static String DEFAULT_STRATEGIE_COMPTE = "DEFAULT";

    UtilisateurScanneur findScanneurSlimById(Integer id);

    List<UtilisateurScanneur> getScanneurs();


    List<UtilisateurScanneur> getAllScanneursDto();

    UtilisateurScanneur saveScanneur(UtilisateurScanneur Scanneur);

    UtilisateurScanneur reinitialiseMotPasse(UtilisateurScanneur Scanneur);

    UtilisateurScanneur createScanneur(UtilisateurScanneur Scanneur);

    UtilisateurScanneur updateScanneur(UtilisateurScanneur Scanneur);

    UtilisateurScanneur deleteScanneur(UtilisateurScanneur Scanneur);

    UtilisateurScanneur getScanneurById(Integer valueOf);

    Optional<UtilisateurScanneur> findScanneurByLogin(String login);

    UtilisateurScanneur getScanneurByLoginAndPassword(String login, String password);

    UtilisateurScanneur getScanneurConnected();
}
