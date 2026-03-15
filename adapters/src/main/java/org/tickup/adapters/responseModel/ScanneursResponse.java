package org.tickup.adapters.responseModel;

import lombok.*;
import org.tickup.adapters.entites.ScanneurEntity;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScanneursResponse {
    private String terminalId;
    private  String refScanneur;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String email;
    private String telephone;
    public static ScanneursResponse toResponse (ScanneurEntity scanneur) {
        return ScanneursResponse.builder()
                .terminalId(scanneur.getTerminalId())
                .refScanneur(scanneur.getRefScanneur())
                .nom(scanneur.getNom())
                .prenom(scanneur.getPrenom())
                .dateNaissance(scanneur.getDateNaissance().toString())
                .email(scanneur.getEmail())
                .telephone(scanneur.getTelephone())
                .build();
    }

}
