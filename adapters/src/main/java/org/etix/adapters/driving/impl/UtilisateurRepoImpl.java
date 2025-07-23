/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.adapters.driving.impl;



import lombok.AllArgsConstructor;
import org.etix.adapters.config.Security.ApplicationUtilisateur;
import org.etix.adapters.driving.repositories.UtilisateurRepository;
import org.etix.adapters.entities.Security.ProfilAccesEntity;
import org.etix.adapters.entities.Security.StrategieCompteEntity;
import org.etix.adapters.entities.Security.UtilisateurEntity;
import org.etix.adapters.utils.PasswordStringGenerator;
import org.etix.adapters.utils.Request;
import org.etix.domain.exceptions.BadRequestException;
import org.etix.domain.exceptions.EntityAlreadyExistsException;
import org.etix.domain.exceptions.NotificationException;
import org.etix.domain.models.Security.ProfilAcces;
import org.etix.domain.models.Security.StrategieCompte;
import org.etix.domain.models.Security.Utilisateur;
import org.etix.domain.models.Security.UtilisateurSlim;
import org.etix.domain.models.request.MailRequest;
import org.etix.domain.ports.driver.MailNotification;
import org.etix.domain.ports.driving.ProfilAccessRepo;
import org.etix.domain.ports.driving.StrategieCompteRepo;
import org.etix.domain.ports.driving.UtilisateurRepo;
import org.etix.domain.utils.UtilisateurValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UtilisateurRepoImpl implements UtilisateurRepo {

    private final static String DEFAULT_LIBELLE = "DEFAULT";

    private final StrategieCompteRepo strategieCompteService;

    private final UtilisateurRepository utilisateurRepository;


    private final PasswordEncoder passwordEncoder;

    private final ProfilAccessRepo profilService;


    private final MailNotification notifyRecipient;


    @Override
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll().stream().map(UtilisateurEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurSlim> getUtilisateursDto() {
        return utilisateurRepository.getUtilisateursToDisplay().stream().map(UtilisateurEntity::toDomainSlim).collect(Collectors.toList());
    }

    @Override
    public List<Utilisateur> getAllUtilisateursDto() {
        return utilisateurRepository.findAll().stream().map(UtilisateurEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {

        // Valider le nom et le(s) prénom(s) de l'utilisateur.
        if (!UtilisateurValidator.isValidNom(utilisateur.getNom())) {
            throw new BadRequestException(
                    String.format("Le nom %s de l'utilisateur n'est pas valide.", utilisateur.getNom()));
        }
        if (!UtilisateurValidator.isValidPrenoms(utilisateur.getPrenom())) {
            throw new BadRequestException(
                    String.format("Le prénom %s de l'utilisateur n'est pas valide.", utilisateur.getPrenom()));
        }
        if (!UtilisateurValidator.isValidGenre(utilisateur.getGenre())) {
            throw new BadRequestException(
                    String.format("Le genre %s de l'utilisateur n'est pas valide.", utilisateur.getGenre()));
        }

        // Valider le téléphone et le statut actif de l'utilisateur.
        if (!UtilisateurValidator.isValidTelephone(utilisateur.getTelephone())) {
            throw new BadRequestException(
                    String.format("Le téléphone %s de l'utilisateur n'est pas valide.", utilisateur.getTelephone()));
        }
        if (!UtilisateurValidator.isValidStatutActif(utilisateur.getStatutActif())) {
            throw new BadRequestException(String.format("Le statut actif %s de l'utilisateur n'est pas valide.",
                    utilisateur.getStatutActif()));
        }

        //  Valider login, l'e-mail.

        if (!UtilisateurValidator.isValidEmail(utilisateur.getEmail())) {
            throw new BadRequestException(
                    String.format("L'adresse email %s de l'utilisateur n'est pas valide.", utilisateur.getEmail()));
        }
        if (!UtilisateurValidator.isValidLogin(utilisateur.getLogin())) {
            throw new BadRequestException(
                    String.format("Le login %s de l'utilisateur n'est pas valide.", utilisateur.getLogin()));
        }


        if (utilisateur.getId() == null) {
            utilisateur = createUtilisateur(utilisateur);
        } else {
            utilisateur = updateUtilisateur(utilisateur);
        }

        return utilisateur;
    }

    @Transactional
    @Override
    public Utilisateur reinitialiseMotPasse(Utilisateur utilisateur) {
        UtilisateurEntity toSave = UtilisateurEntity.toEntity(getById(utilisateur.getId()));
        // superieurHierachiqueId
        String randomPassword = null;
        Optional<StrategieCompte> optionalStrategie = strategieCompteService.findByLibelleIgnoreCase(DEFAULT_LIBELLE);
        if (optionalStrategie.isPresent()) {
            randomPassword = PasswordStringGenerator.generatePassword(StrategieCompteEntity.toEntity(optionalStrategie.get()));
        } else {
            randomPassword = "Etix@2025";
        }
        toSave.setPassword(passwordEncoder.encode(randomPassword));
        toSave.setPassword(randomPassword);
        updateUtilisateur(toSave.toDomain());


        return toSave.toDomain();
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByLogin(utilisateur.getLogin()).map(UtilisateurEntity::toDomain);
        if (!optionalUtilisateur.isEmpty()) {
            throw new EntityAlreadyExistsException(
                    String.format("Un utilisateur existe avec le login %s ", utilisateur.getLogin()));
        }


        optionalUtilisateur = utilisateurRepository.findByEmail(utilisateur.getEmail()).map(UtilisateurEntity::toDomain);
        if (!optionalUtilisateur.isEmpty()) {
            throw new EntityAlreadyExistsException(
                    String.format("Un utilisateur existe avec l'adresse email %s", utilisateur.getEmail()));
        }

        // Construire le mot de passe par defaut.
        String randomPassword = null;
        Optional<StrategieCompte> optionalStrategie = strategieCompteService.findByLibelleIgnoreCase(DEFAULT_LIBELLE);
        if (optionalStrategie.isPresent()) {
            randomPassword = PasswordStringGenerator.generatePassword(StrategieCompteEntity.toEntity(optionalStrategie.get()));
        } else {
            randomPassword = "Etix@2025";
        }

        LocalDateTime dateOperation = LocalDateTime.now();
        utilisateur.setCreatedAt(dateOperation);
        utilisateur.setStatutActif(Boolean.TRUE);
        utilisateur.setPassword(passwordEncoder.encode(randomPassword));
        utilisateur = utilisateurRepository.save(UtilisateurEntity.toEntity(utilisateur)).toDomain();


        utilisateur.setPassword(randomPassword);

        MailRequest mailResquest = new MailRequest();
        mailResquest.setRecipients(new String[]{utilisateur.getEmail()});
        mailResquest.setObject("eTix  - Création de compte");
        Map<String, String> messageParams = new HashMap<String, String>();
        String connexionLink = Request.urlBase() + "/auth/connexion.xhtml";
        messageParams.put("NOM_OPERATEUR", utilisateur.getPrenom());
        messageParams.put("USERNAME", utilisateur.getLogin());
        messageParams.put("CONNEXION_LINK", connexionLink);
        messageParams.put("PASSWORD", randomPassword);

        try {
            mailResquest.setContent(notifyRecipient.format("creation-compte-agent.html", messageParams));
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new NotificationException("La compilation du mail de notification a échoué.");
        }
        boolean mailResponse = notifyRecipient.envoyer(mailResquest);
        if (!mailResponse) {
            throw new NotificationException("L'envoi des instructions de changement de mot de passe n'a pas abouti.");
        }
        return utilisateur;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        Utilisateur toUpdate = getById(utilisateur.getId());
        if (!toUpdate.getLogin().equals(utilisateur.getLogin())
                && (utilisateurRepository.countByLogin(utilisateur.getLogin()) > 0)) {
            throw new EntityAlreadyExistsException(
                    String.format("Un utilisateur existe avec le login %s", utilisateur.getLogin()));
        }

        if (!toUpdate.getEmail().equals(utilisateur.getEmail())
                && (utilisateurRepository.countByEmail(utilisateur.getEmail()) > 0)) {
            throw new EntityAlreadyExistsException(
                    String.format("Un utilisateur existe avec l'email %s", utilisateur.getEmail()));

        }


        utilisateur.setDateDernModification(LocalDateTime.now());
        utilisateur.setCreatedAt(toUpdate.getCreatedAt());
        utilisateur.setPassword(toUpdate.getPassword());
        utilisateur.setProfils(toUpdate.getProfils());
        return utilisateurRepository.save(UtilisateurEntity.toEntity(utilisateur)).toDomain();
    }


    @Override
    public Utilisateur deleteUtilisateur(Utilisateur utilisateur) {
        Utilisateur toDelete = getById(utilisateur.getId());
        toDelete.setDeletedAt(LocalDateTime.now());
        toDelete.setStatutActif(Boolean.FALSE);
        return utilisateurRepository.save(UtilisateurEntity.toEntity(toDelete)).toDomain();
    }


    @Override
    public Utilisateur getById(Integer id) {
        return utilisateurRepository.rechercherUtilisateurParPrincipal(id).toDomain();
    }

    private Optional<Utilisateur> findById(Integer id) {
        return utilisateurRepository.findById(id).map(UtilisateurEntity::toDomain);
    }


    @Override
    public Utilisateur updateProfilAccess(Utilisateur utilisateur, Integer[] profilSelectedId) {
        Utilisateur toUpdate = getById(utilisateur.getId());
        Set<ProfilAcces> profilSelected = new HashSet<>();
        for (ProfilAcces profil : profilService.findByIdIn(profilSelectedId)) {
            profilSelected.add(profil);
        }
        toUpdate.setProfils(profilSelected);
        toUpdate.setDateDernModification(LocalDateTime.now());
        return utilisateurRepository.save(UtilisateurEntity.toEntity(toUpdate)).toDomain();
    }


    @Override
    public List<UtilisateurSlim> utilisateuersSlimDtoSelonProfil(ProfilAcces profilAcces) {
        return utilisateurRepository.getUtilisateurByProfil(ProfilAccesEntity.toEntity(profilAcces)).stream()
                .map(UtilisateurEntity::toDomainSlim)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Utilisateur> findByLogin(String login) {
        return utilisateurRepository.findByLogin(login).map(UtilisateurEntity::toDomain);
    }

    @Override
    public Utilisateur getUtilisateurByLoginAndPassword(String login, String password) {
        return utilisateurRepository.findByLoginAndAndPassword(login, password).toDomain();
    }

    @Override
    public Utilisateur getUtilisateurConnected() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            if (principal instanceof ApplicationUtilisateur user) {
                System.out.println(user.getUtilisateur());
                return user.getUtilisateur().toDomain();
            }
//            Utilisateur principal = (Utilisateur) SecurityContextHolder.getContext().getAuthentication()
//                    .getPrincipal();
//            return principal;
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

}
