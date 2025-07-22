package org.etix.domain.models.enumerations;

import java.util.List;

public enum TypeLog {

    ERREUR, ACCES, INFO;

    List<TypeLog> getAll() {
        return List.of(TypeLog.values());
    }

}
