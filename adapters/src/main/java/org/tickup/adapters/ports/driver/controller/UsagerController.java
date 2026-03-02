package org.tickup.adapters.ports.driver.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tickup.adapters.ports.driver.apiException.ResponseApi;
import org.tickup.adapters.ports.driver.facades.UsagerFacade;
import org.tickup.adapters.requestModel.UsagerRequest;
import org.tickup.domain.requests.UsagersRequest;

@RestController
@Slf4j
@RequestMapping("/api/v1/usagers")
@AllArgsConstructor
public class UsagerController {

    private final UsagerFacade usagerFacade;



    @PostMapping("/usager")
    public ResponseEntity<ResponseApi> saveUsager(@RequestBody UsagerRequest request) {
        log.info("UsagerRequest : {}", request);
        UsagersRequest domain = UsagerRequest.toDomain(request);
        log.info("UsagerRequest domain : {}", domain);
        usagerFacade.saveUsager(domain);
        return new ResponseEntity<>(new ResponseApi("Success", HttpStatus.OK.value(), null), HttpStatus.OK);
    }

}
