package org.etix.adapters.config.Security;



import org.etix.adapters.entities.Security.UtilisateurEntity;

import java.util.Optional;

public interface PasswordResetTokenService {

    /**
     * @param token
     * @return
     */
    int countByToken(String token);

    /**
     * @param token
     * @param utilisateur
     * @return
     */
    PasswordResetToken saveToken(String token, UtilisateurEntity utilisateur);

    /**
     * @param prToken
     * @return
     */
    PasswordResetToken updateToken(PasswordResetToken prToken);

    /**
     * @param token
     * @return
     */
    Optional<PasswordResetToken> findByToken(String token);

    /**
     * Vérifier l'etat du token de changement de mot de passe (PRESENCE | USAGE | EXPIRATION)
     *
     * @param token
     * @return
     */
    boolean verifyToken(String token);

}
