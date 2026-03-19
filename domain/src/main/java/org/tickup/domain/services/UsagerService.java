package org.tickup.domain.services;

import org.tickup.domain.apiRequest.MailRequest;
import org.tickup.domain.ddd.DomaineService;
import org.tickup.domain.exceptions.BadRequestException;
import org.tickup.domain.exceptions.EntityAlreadyExistsException;
import org.tickup.domain.models.Usager;
import org.tickup.domain.models.Utilisateur;
import org.tickup.domain.ports.driver.UtilisateurPortDriving;
import org.tickup.domain.ports.driving.UsagerDriving;
import org.tickup.domain.ports.driver.UsagerPortDriver;
import org.tickup.domain.ports.driving.recipients.NotifyRecipient;
import org.tickup.domain.requests.UsagersRequest;
import org.tickup.domain.responses.UsagerResponse;
import org.tickup.domain.utils.AppUtils;
import org.tickup.domain.utils.RandomPasswordGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@DomaineService

public class UsagerService implements UsagerPortDriver {
    private final UsagerDriving usagerPortDriving;
    private final UtilisateurPortDriving utilisateurPortDriving;
    private final NotifyRecipient notifyRecipient;



    public UsagerService(UsagerDriving usagerPortDriving, UtilisateurPortDriving utilisateurPortDriving, NotifyRecipient notifyRecipient) {
        this.usagerPortDriving = usagerPortDriving;
        this.utilisateurPortDriving = utilisateurPortDriving;
        this.notifyRecipient = notifyRecipient;
    }

    private static void validateUsager(UsagersRequest usagerRequest) {
        if (usagerRequest.getNom() == null || usagerRequest.getNom().isEmpty()) {
            throw new BadRequestException("Usager's name is required");
        }
        if ((usagerRequest.getTelephone() == null)) {
            throw new BadRequestException("N° tel is required");
        }
        if (usagerRequest.getDateNaissance()== null) {
            throw new BadRequestException("Date naissance usager  is required");
        }
        if (usagerRequest.getPrenom() == null || usagerRequest.getPrenom().isEmpty()) {
            throw new BadRequestException("Usager's surname is required");
        }

        if (usagerRequest.getEmail() == null || usagerRequest.getEmail().isEmpty()) {
            throw new BadRequestException("Usager's address email is required");
        }


    }

    private static Usager buildUsagerToSave(UsagersRequest usagerRequest) {

        return new Usager.Builder()
                .refUsager(AppUtils.generateReference("USER-"))
                .nom(usagerRequest.getNom())
                .prenom(usagerRequest.getPrenom())
                .telephone(usagerRequest.getTelephone())
                .email(usagerRequest.getEmail())
                .dateNaissance(usagerRequest.getDateNaissance())
                .build();
    }



    @Override
    public void saveUsager(UsagersRequest usagerRequest) {
        System.out.println("saveUtilisateur usagerRequest :::: " + usagerRequest);
        validateUsager(usagerRequest);

        // Check if email already exists
        Usager usagerExist = usagerPortDriving.findByEmail(usagerRequest.getEmail());
        if (usagerExist != null) {
            throw new EntityAlreadyExistsException("Un utilisateur existe déjà avec cet email");
        }

        // Check if merchant name exists (if applicable)
        // if (usagerRequest.getMerchantName() != null) {
        //     Merchand merchandNameExist = merchandPortDriving.findByMerchantName(usagerRequest.getMerchantName());
        //     if (merchandNameExist != null) {
        //         throw new EntityAlreadyExistsException("Un marchand existe déjà avec ce nom");
        //     }
        // }

        // Call external service to create merchant (if needed)
        // MerchandResponse merchandResponse = null;
        // try {
        //     merchandResponse = paySkyRecipient.createMerchandRequest(usagerRequest);
        //     if (merchandResponse != null && !merchandResponse.getSuccess()) {
        //         throw new BadRequestException(merchandResponse.getMessage());
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     throw new RuntimeException("Erreur lors de la création du marchand chez PaySky");
        // }

        // Save the usager
        Usager usagerSaved = usagerPortDriving.saveUsager(
                buildUsagerToSave(usagerRequest)
        );

        // Generate default password
        String password = RandomPasswordGenerator.generateDefault(8);

        // Create and save utilisateur
        Utilisateur userSave = utilisateurPortDriving.createUtilisateur(
                new Utilisateur.Builder()
                        .login(usagerSaved.getEmail())
                        .password(password)
                        .refUsager(usagerSaved.getRefUsager()) // Changed from refMerchent
                        .isFisrtConnection(true) // Fixed typo: isFisrtConnection -> isFirstConnection
                        .build()
        );

        // Send access credentials
        envoyerAccesUsager(userSave.getLogin(), password);
    }
    private void envoyerAccesUsager(String email, String password) {
        try {
            MailRequest mailRequest = new MailRequest();

            Map<String, String> messageParams = new HashMap<>();
            messageParams.put("{{LOGIN}}", email);
            messageParams.put("{{PASSWORD}}", password);
            mailRequest.setRecipients(new String[]{email});
            mailRequest.setObject("Bienvenue sur votre espace marchand.");
//            String content = "Bonjour,\n\n" +
//                    "Votre compte marchand a été créé avec succès. Voici vos informations de connexion :\n\n" +
//                    "Login : " + merchantRequest.getEmail() + "\n" +
//                    "Mot de passe : {{PASSWORD}}\n\n" + password +
//                    "Veuillez vous connecter à votre espace marchand pour gérer vos transactions et votre profil.\n\n" +
//                    "Cordialement,\n" +
//                    "L'équipe de support";
            String content = notifyRecipient.formatMail("inscription-usager.html", messageParams);
            mailRequest.setContent(content);
            if (content == null || content.isBlank()) {
                throw new RuntimeException("Le contenu du mail est vide ou le template est introuvable");
            }

            mailRequest.setContent(content);
            notifyRecipient.envoyerMail(mailRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur est survenue");
        }
    }


}
