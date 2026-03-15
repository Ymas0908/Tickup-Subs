package org.tickup.domain.services;

import org.tickup.domain.ddd.DomaineService;
import org.tickup.domain.models.Scanneur;
import org.tickup.domain.models.Utilisateur;
import org.tickup.domain.models.UtilisateurScanneur;
import org.tickup.domain.ports.driver.UtilisateurScanneurPortDriving;
import org.tickup.domain.ports.driving.GererUtilisateurScanneurPortDriver;

import java.util.List;
import java.util.Optional;
@DomaineService
public class GestionUtilisateurScanneurService implements GererUtilisateurScanneurPortDriver {

    private final UtilisateurScanneurPortDriving utilisateurScanneurPortDriving;

    public GestionUtilisateurScanneurService(UtilisateurScanneurPortDriving utilisateurScanneurPortDriving) {
        this.utilisateurScanneurPortDriving = utilisateurScanneurPortDriving;
    }


    @Override
    public UtilisateurScanneur findScanneurSlimById(Integer id) {
        return utilisateurScanneurPortDriving.findUtilisateurScanneurSlimById(id);
    }

    @Override
    public List<UtilisateurScanneur> getScanneurs() {
        return null;

    }

    @Override
    public List<UtilisateurScanneur> getAllScanneursDto() {
        return null;
    }

    @Override
    public UtilisateurScanneur saveScanneur(UtilisateurScanneur Scanneur) {
        return null;
    }

    @Override
    public UtilisateurScanneur reinitialiseMotPasse(UtilisateurScanneur Scanneur) {
        return null;
    }

    @Override
    public UtilisateurScanneur createScanneur(UtilisateurScanneur Scanneur) {
        return null;
    }

    @Override
    public UtilisateurScanneur updateScanneur(UtilisateurScanneur Scanneur) {
        return null;
    }

    @Override
    public UtilisateurScanneur deleteScanneur(UtilisateurScanneur Scanneur) {
        return null;
    }

    @Override
    public UtilisateurScanneur getScanneurById(Integer valueOf) {
        return null;
    }

    @Override
    public Optional<UtilisateurScanneur> findScanneurByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public UtilisateurScanneur getScanneurByLoginAndPassword(String login, String password) {
        return null;
    }

    @Override
    public UtilisateurScanneur getScanneurConnected() {
        return utilisateurScanneurPortDriving.getUtilisateurScanneurConnected();
    }
}
