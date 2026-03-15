package org.tickup.domain.ports.driving;

import org.tickup.domain.models.Scanneur;

public interface ScanneurRepo {
    Scanneur save(Scanneur scanneur);
}
