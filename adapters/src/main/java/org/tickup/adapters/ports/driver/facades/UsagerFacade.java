package org.tickup.adapters.ports.driver.facades;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tickup.adapters.config.AuthService;
import org.tickup.adapters.requestModel.SignUpRequest;
import org.tickup.adapters.utils.RandomPasswordGenerator;
import org.tickup.domain.apiRequest.MailRequest;
import org.tickup.domain.ports.driver.NotifyDriver;
import org.tickup.domain.ports.driver.UsagerPortDriver;
import org.tickup.domain.requests.UsagersRequest;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class UsagerFacade {
    @Autowired
    private UsagerPortDriver usagerPortDriver;
    @Autowired
    private AuthService authService;
    @Autowired

    private NotifyDriver notifyDriver;

    public void saveUsager (UsagersRequest usagerRequest) {
        String saveUsager = usagerPortDriver.saveUsager(usagerRequest);
        if (saveUsager != null) {
            String password = RandomPasswordGenerator.generateDefault(8);
            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setLogin(usagerRequest.getEmail());
            signUpRequest.setPassword(password);
//            System.out.println("User created with login: " + signUpRequest.getLogin() + " and password: " + password);
            signUpRequest.setRefUsager(saveUsager);
            authService.singup(signUpRequest);
            envoyerAccesUsager(usagerRequest, password);

        } else {
            log.error("Failed to save Usager");
        }

    }
    private void envoyerAccesUsager(UsagersRequest usagerRequest, String password) {
        try {
            MailRequest mailRequest = new MailRequest();

            Map<String, String> messageParams = new HashMap<>();
            messageParams.put("{{LOGIN}}", usagerRequest.getEmail());
            messageParams.put("{{PASSWORD}}", password);
            mailRequest.setRecipients(new String[]{usagerRequest.getEmail()});
            mailRequest.setObject("Inscription sur Tickup");
//            String content = "Bonjour,\n\n" +
//                    "Votre compte marchand a été créé avec succès. Voici vos informations de connexion :\n\n" +
//                    "Login : " + merchantRequest.getEmail() + "\n" +
//                    "Mot de passe : {{PASSWORD}}\n\n" + password +
//                    "Veuillez vous connecter à votre espace marchand pour gérer vos transactions et votre profil.\n\n" +
//                    "Cordialement,\n" +
//                    "L'équipe de support";
            String content = notifyDriver.formatMail("inscription-usager.html", messageParams);
            mailRequest.setContent(content);
            if (content == null || content.isBlank()) {
                throw new RuntimeException("Le contenu du mail est vide ou le template est introuvable");
            }

            mailRequest.setContent(content);
            notifyDriver.envoyerMail(mailRequest);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new RuntimeException("Une erreur est survenue");
        }
    }


}
