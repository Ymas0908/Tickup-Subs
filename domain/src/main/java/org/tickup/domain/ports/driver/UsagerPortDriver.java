package org.tickup.domain.ports.driver;

import org.tickup.domain.requests.UsagersRequest;

public interface UsagerPortDriver {

    void saveUsager(UsagersRequest usagerRequest);
}
