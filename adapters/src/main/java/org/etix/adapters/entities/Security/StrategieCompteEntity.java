/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.adapters.entities.Security;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.etix.domain.models.Security.StrategieCompte;
import org.etix.domain.utils.Constant;
import org.etix.domain.utils.PasswordRegExGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "strategie_compte")
public class StrategieCompteEntity implements Serializable {

    private static final long serialVersionUID = 4821791910295299999L;

    private static final String SPECIAL_CHARS = "#?!@$%^&*-";
    @Column(name = "created_date")
    protected LocalDateTime createdAt;
    @Column(name = "date_dern_modification")
    protected LocalDateTime dateDernModification;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "libelle", length = 150, nullable = false)
    private String libelle;

    @Column(name = "reference", length = 150, nullable = false)
    private String reference;

    @Column(name = "duree_alerte_password")
    private Integer dureeAlertePassword;

    @Column(name = "duree_validite_password")
    private Integer dureeValiditePassword;

    @Column(name = "long_min_password")
    private Integer longueurMinimalePassword;

    @Column(name = "nbr_caractere_majuscule")
    private Integer nbreCaractereMajuscule;

    @Column(name = "nbr_caractere_speciaux")
    private Integer nbrCaractereSpeciaux;

    @Column(name = "nbr_chiffre")
    private Integer nbrChiffre;

    @Column(name = "nbr_tentativ_echec")
    private Integer nbrTentativeEchec;

    @Column(name = "generated_regex", length = 180, nullable = false)
    private String generatedRegex;

    public static StrategieCompteEntity toEntity(StrategieCompte strategiecompte) {
        return new StrategieCompteEntityBuilder()
                .createdAt(strategiecompte.getCreatedAt())
                .dateDernModification(strategiecompte.getDateDernModification())
                .id(strategiecompte.getId())
                .libelle(strategiecompte.getLibelle())
                .reference(strategiecompte.getReference())
                .dureeAlertePassword(strategiecompte.getDureeAlertePassword())
                .dureeValiditePassword(strategiecompte.getDureeValiditePassword())
                .longueurMinimalePassword(strategiecompte.getLongueurMinimalePassword())
                .nbreCaractereMajuscule(strategiecompte.getNbreCaractereMajuscule())
                .nbrCaractereSpeciaux(strategiecompte.getNbrCaractereSpeciaux())
                .nbrChiffre(strategiecompte.getNbrChiffre())
                .nbrTentativeEchec(strategiecompte.getNbrTentativeEchec())
                .generatedRegex(strategiecompte.getGeneratedRegex())
                .build();
    }

    /**
     * @return
     */
    public String generateRegex() {
        return PasswordRegExGenerator.generateRegex(longueurMinimalePassword, nbreCaractereMajuscule,
                nbrCaractereSpeciaux, nbrChiffre, SPECIAL_CHARS);
    }

    /**
     * @return
     */
    public String generateReference() {
        return String.format("LM%s-NM%s-NS%s-NC%s-NT%s-DV%s-DA%s", this.getLongueurMinimalePassword(),
                this.getNbreCaractereMajuscule(), this.getNbrCaractereSpeciaux(), this.getNbrChiffre(),
                this.getNbrTentativeEchec(), this.getDureeValiditePassword(), this.getDureeAlertePassword());
    }

    public boolean isRemovable() {
        return id != null && !Constant.DEFAULT_CODE.equals(libelle);
    }

    public boolean isEditable() {
        return !Constant.DEFAULT_CODE.equals(libelle);
    }

    public StrategieCompte toDomain() {
        return new StrategieCompte.Builder()
                .createdAt(createdAt)
                .dateDernModification(dateDernModification)
                .id(id)
                .libelle(libelle)
                .reference(reference)
                .dureeAlertePassword(dureeAlertePassword)
                .dureeValiditePassword(dureeValiditePassword)
                .longueurMinimalePassword(longueurMinimalePassword)
                .nbreCaractereMajuscule(nbreCaractereMajuscule)
                .nbrCaractereSpeciaux(nbrCaractereSpeciaux)
                .nbrChiffre(nbrChiffre)
                .nbrTentativeEchec(nbrTentativeEchec)
                .generatedRegex(generatedRegex)
                .build();
    }
}
