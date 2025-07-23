package org.etix.adapters.config.Security;

import lombok.*;
import org.etix.domain.models.Security.UtilisateurSlim;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UtilisateurSummaryDto extends UtilisateurSlim {

    private static final long serialVersionUID = 2522324799854641035L;

    // Meta data
    protected LocalDateTime createdAt;
    protected LocalDateTime deletedAt;
    protected LocalDateTime dateDernConnexion;
    protected LocalDateTime dateDernModification;
    protected LocalDateTime datePasswordExpire;

    // Agence de rattachement
    protected Integer agenceId;
    protected String agenceCode;
    protected String agenceLibelle;

    // Fonction de rattachement
    protected Integer fonctionId;
    protected String fonctionLibelle;
    protected Boolean fonctionStatutActif;

    // Supérieur hiérachique
    protected Integer superieurHierachiqueId;
    protected String superieurHierachiqueEmail;
    protected String superieurHierachiqueNom;
    protected String superieurHierachiquePrenom;
    protected String superieurHierachiqueCodeExploitant;

}
