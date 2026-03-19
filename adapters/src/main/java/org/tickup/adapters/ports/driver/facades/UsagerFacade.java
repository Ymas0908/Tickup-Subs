package org.tickup.adapters.ports.driver.facades;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tickup.adapters.config.AuthService;
import org.tickup.adapters.requestModel.SignUpRequest;
import org.tickup.adapters.utils.RandomPasswordGenerator;
import org.tickup.domain.apiRequest.MailRequest;
import org.tickup.domain.ports.driver.NotifyDriver;
import org.tickup.domain.ports.driver.UsagerPortDriver;
import org.tickup.domain.requests.UsagersRequest;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class UsagerFacade {
    @Autowired
    private UsagerPortDriver usagerPortDriver;


    public void saveUsager (UsagersRequest usagerRequest) {
        usagerPortDriver.saveUsager(usagerRequest);
    }



}
