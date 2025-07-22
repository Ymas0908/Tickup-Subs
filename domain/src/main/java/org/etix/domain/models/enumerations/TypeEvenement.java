package org.etix.domain.models.enumerations;

import java.util.List;

public enum TypeEvenement {
    CONCERT,
    THEATRE,
    SHOWCASE,
    CINEMA,
    FESTIVAL,
    EXPOSITION,
    RELEASE_PARTY,

    MATCH;

    public static List<TypeEvenement> getTypes() {
        return List.of(TypeEvenement.values());
    }
}
