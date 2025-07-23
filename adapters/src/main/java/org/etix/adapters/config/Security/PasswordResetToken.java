package org.etix.adapters.config.Security;

import jakarta.persistence.*;
import lombok.*;
import org.etix.adapters.entities.Security.UtilisateurEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken implements Serializable {

    private static final long serialVersionUID = -6396387396974906157L;


    @Column(name = "date_expiration", nullable = false)
    private LocalDateTime dateExpiration;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateObtention;

    @Column(name = "token", nullable = true)
    private String token;

    @Column(name = "used", nullable = true)
    private boolean used;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = true)
    private UtilisateurEntity utilisateur;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PasswordResetToken)) {
            return false;
        }
        PasswordResetToken annotation = (PasswordResetToken) object;
        return Objects.equals(this.getId(), annotation.getId());
    }
}