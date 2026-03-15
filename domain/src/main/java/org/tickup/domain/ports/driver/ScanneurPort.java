package org.tickup.domain.ports.driver;

import org.tickup.domain.requests.ScanneursRequest;

public interface ScanneurPort {
    String saveScanneur(ScanneursRequest scanneursRequest);

}
