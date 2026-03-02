package org.tickup.adapters.requestModel;

import lombok.*;
import org.tickup.domain.requests.UsagersRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsagerRequest {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String dateNaissance;

    public static UsagersRequest toDomain (UsagerRequest usagerRequest) {
        return  new UsagersRequest.Builder()
                .nom(usagerRequest.getNom())
                .prenom(usagerRequest.getPrenom())
                .email(usagerRequest.getEmail())
                .telephone(usagerRequest.getTelephone())
                .dateNaissance(usagerRequest.getDateNaissance())
                .build();
    }

}
