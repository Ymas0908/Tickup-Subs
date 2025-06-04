package org.etix.domain.models.enumerations;

import java.util.List;

public enum StatutEvenement {
    A_VENIR,
    EN_COURS,
    TERMINE;

    static List<StatutEvenement> statutEvenementList() {
        return List.of(StatutEvenement.values());
    }

    public String getLabel() {
        return name().replace('_', ' ');
    }

    public String getSeverity() {
        return switch (this) {
            case A_VENIR -> "info";     // bleu
            case EN_COURS -> "warning"; // orange
            case TERMINE -> "success";  // vert
        };
    }
}
