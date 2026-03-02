package org.tickup.domain.models.enums;

import java.util.List;

public enum StatutTerminal {
    ACTIF,
    INACTIF,
    BLACKLISTE;

    public static List<StatutTerminal> getAll(){
        return List.of(StatutTerminal.values());
    }
}
