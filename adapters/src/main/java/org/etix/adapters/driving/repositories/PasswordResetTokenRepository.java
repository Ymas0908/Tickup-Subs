package org.etix.adapters.driving.repositories;


import org.etix.adapters.config.Security.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    int countByToken(String token);

    @Query("SELECT p FROM PasswordResetToken p LEFT JOIN FETCH p.utilisateur u"
            + " LEFT JOIN FETCH u.profils "
            + " WHERE p.token=:token")
    Optional<PasswordResetToken> findByToken(String token);

}