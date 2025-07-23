package org.etix.adapters.config.Security;

import lombok.*;
import org.etix.adapters.entities.Security.ProfilAccesEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UtilisateurDto extends UtilisateurSummaryDto {

    private static final long serialVersionUID = -5786601110920662609L;
    protected String passWordGenerer;
    protected Set<ProfilAccesEntity> profils = new HashSet<>();


}
