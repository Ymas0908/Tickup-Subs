package org.tickup.adapters.requestModel;

import lombok.*;
import org.tickup.domain.requests.ScanneursRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ScanneurRequest {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String dateNaissance;

    public static ScanneursRequest toDomain (ScanneurRequest usagerRequest) {
        return  new ScanneursRequest.Builder()
                .nom(usagerRequest.getNom())
                .prenom(usagerRequest.getPrenom())
                .email(usagerRequest.getEmail())
                .telephone(usagerRequest.getTelephone())
                .dateNaissance(usagerRequest.getDateNaissance())
                .build();
    }

}
