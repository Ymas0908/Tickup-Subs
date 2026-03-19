package org.tickup.domain.ports.driving;

import org.tickup.domain.models.Usager;

public interface UsagerDriving {
    Usager saveUsager(Usager usager);
    Usager findByEmail(String email);
}
