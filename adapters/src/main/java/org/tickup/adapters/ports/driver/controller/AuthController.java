package org.tickup.adapters.ports.driver.controller;


import org.tickup.adapters.config.AuthService;
import org.tickup.adapters.requestModel.*;
import org.tickup.adapters.responseModel.LoginResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.tickup.adapters.utils.AppUtils;


@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Gestion d'authentification")
@Slf4j
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/users/signUp")
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        authService.singup(signUpRequest);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest loginRequest, @RequestHeader(value = "X-Terminal-Id", required = false) String terminalId, @RequestHeader(value = "X-Terminal-Model", required = false) String terminalModel, @RequestHeader(value = "X-Terminal-Marque", required = false) String terminalMarque, HttpServletRequest request) throws AccessDeniedException {
        LoginResponse loginResponse = authService.login(loginRequest.getLogin(), loginRequest.getPassword(), terminalId, terminalModel, terminalMarque, AppUtils.getIpAddress(request));
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) throws AccessDeniedException {LoginResponse loginResponse = authService.refreshToken(refreshTokenRequest.getRefreshToken());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/users/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid UpdateUserPasswordRequest updateUserPasswordRequest) {
        authService.updatePassword(updateUserPasswordRequest);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/users/update-password-forget")
    public ResponseEntity<?> updatePasswordForget(@RequestBody @Valid UpdateUserPasswordForgetRequest updateUserPasswordForgetRequest) throws AccessDeniedException {
        authService.updatePasswordForget(updateUserPasswordForgetRequest);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/users/userconnected")
    public ResponseEntity<?> getUserConnected() {
        return new ResponseEntity<>(authService.getUserConnected(), HttpStatus.OK);
    }
}
