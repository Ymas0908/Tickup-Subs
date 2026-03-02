package org.tickup.adapters.responseModel;

import lombok.*;
import org.tickup.adapters.entites.UsagerEntity;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsagersResponse {
    private String terminalId;
    private  String refUsager;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String email;
    private String telephone;

    public static UsagersResponse toResponse (UsagerEntity usager) {
        return UsagersResponse.builder()
                .terminalId(usager.getTerminalId())
                .refUsager(usager.getRefUsager())
                .nom(usager.getNom())
                .prenom(usager.getPrenom())
                .dateNaissance(usager.getDateNaissance().toString())
                .email(usager.getEmail())
                .telephone(usager.getTelephone())
                .build();
    }


}
