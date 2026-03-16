package org.tickup.adapters.ports.driver.facades;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tickup.adapters.config.AuthService;
import org.tickup.adapters.requestModel.SignUpRequest;
import org.tickup.adapters.requestModel.SignupScanneurRequest;
import org.tickup.adapters.utils.RandomPasswordGenerator;
import org.tickup.domain.apiRequest.MailRequest;
import org.tickup.domain.ports.driver.NotifyDriver;
import org.tickup.domain.ports.driver.ScanneurPort;
import org.tickup.domain.requests.ScanneursRequest;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
public class ScanneurFacade {
    @Autowired
    private ScanneurPort scanneurPort;
    @Autowired
    private AuthService authService;
    @Autowired

    private NotifyDriver notifyDriver;

    public void saveScanneur (ScanneursRequest scanneursRequest) {
        String saveScanneur = scanneurPort.saveScanneur(scanneursRequest);
        if (saveScanneur != null) {
            String password = RandomPasswordGenerator.generateDefault(8);
            SignupScanneurRequest signupScanneurRequest = new SignupScanneurRequest();
            signupScanneurRequest.setLogin(scanneursRequest.getEmail());
            signupScanneurRequest.setPassword(password);
//            System.out.println("User created with login: " + signUpRequest.getLogin() + " and password: " + password);
            signupScanneurRequest.setRefScanneur(saveScanneur);
            authService.singupScanneur(signupScanneurRequest);
            envoyerAccesUsager(scanneursRequest, password);

        } else {
            log.error("Failed to save scanneur");
        }

    }
    private void envoyerAccesUsager(ScanneursRequest scanneursRequest, String password) {
        try {
            MailRequest mailRequest = new MailRequest();

            Map<String, String> messageParams = new HashMap<>();
            messageParams.put("{{LOGIN}}", scanneursRequest.getEmail());
            messageParams.put("{{PASSWORD}}", password);
            mailRequest.setRecipients(new String[]{scanneursRequest.getEmail()});
            mailRequest.setObject("Inscription sur Tickup");
//            String content = "Bonjour,\n\n" +
//                    "Votre compte marchand a été créé avec succès. Voici vos informations de connexion :\n\n" +
//                    "Login : " + merchantRequest.getEmail() + "\n" +
//                    "Mot de passe : {{PASSWORD}}\n\n" + password +
//                    "Veuillez vous connecter à votre espace marchand pour gérer vos transactions et votre profil.\n\n" +
//                    "Cordialement,\n" +
//                    "L'équipe de support";
            String content = notifyDriver.formatMail("inscription-scanneur.html", messageParams);
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
