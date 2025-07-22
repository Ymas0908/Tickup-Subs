package org.etix.domain.models.enumerations;

import java.util.List;

public enum Genre {
    MASCULIN("Masculin"),
    FEMININ("Féminin");

    public final String label;

    Genre(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static List<Genre> getAll() {
        return List.of(values());
    }

    @Override
    public String toString() {
        return name();
    }
}
