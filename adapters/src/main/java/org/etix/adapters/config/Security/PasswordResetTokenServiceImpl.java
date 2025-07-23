package org.etix.adapters.config.Security;

import lombok.RequiredArgsConstructor;
import org.etix.adapters.driving.repositories.PasswordResetTokenRepository;
import org.etix.adapters.entities.Security.UtilisateurEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

;


@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {


    private final PasswordResetTokenRepository prtRepository;


    @Override
    public int countByToken(String token) {
        return prtRepository.countByToken(token);
    }


    /**
     * @param token
     * @return
     */
    @Override
    public Optional<PasswordResetToken> findByToken(String token) {
        return prtRepository.findByToken(token);
    }

    /**
     * Permet de compiler et de sauvegarder un token de changement de mot de passe.
     *
     * @param token
     * @param utilisateur
     * @return
     */
    @Override
    public PasswordResetToken saveToken(String token, UtilisateurEntity utilisateur) {
        LocalDateTime startDate = LocalDateTime.now();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setId(null);
        resetToken.setUsed(false);
        resetToken.setToken(token);
        resetToken.setUtilisateur(utilisateur);
        resetToken.setDateObtention(startDate);
        resetToken.setDateExpiration(startDate.plusDays(1));
        return prtRepository.save(resetToken);
    }

    /**
     * @param prToken
     * @return
     */
    @Override
    public PasswordResetToken updateToken(PasswordResetToken prToken) {
        return prtRepository.save(prToken);
    }


    @Override
    public boolean verifyToken(String token) {
        PasswordResetToken presentToken = null;
        Optional<PasswordResetToken> optionalPrt = findByToken(token);
        if (!optionalPrt.isPresent()) {
            return true;
        }
        presentToken = optionalPrt.get();
        if (presentToken.isUsed()) {
            return true;
        }
        if (LocalDateTime.now().isAfter(presentToken.getDateExpiration())) {
            return true;
        }
        return false;
    }
}
