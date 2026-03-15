package org.tickup.adapters.ports.driver.controller;


import org.tickup.adapters.config.AuthService;
import org.tickup.adapters.requestModel.*;
import org.tickup.adapters.responseModel.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.tickup.adapters.responseModel.LoginScanneurResponse;
import org.tickup.adapters.utils.AppUtils;


@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Gestion d'authentification", description = "API pour l'authentification des utilisateurs")
@Slf4j
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/users/signUp")
    @Operation(summary = "Inscription d'un nouvel utilisateur", description = "Crée un nouveau compte utilisateur")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides"),
        @ApiResponse(responseCode = "409", description = "Utilisateur existe déjà")
    })
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        authService.singup(signUpRequest);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    @PostMapping("/scanneurs/signUp")
    @Operation(summary = "Inscription d'un nouvel utilisateur scanneur ", description = "Crée un nouveau compte utilisateur scanneur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Utilisateur scanneur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "409", description = "Utilisateur scanneur existe déjà")
    })
    public ResponseEntity<?> signupScanneur(@RequestBody @Valid SignupScanneurRequest signUpRequest) {
        authService.singupScanneur(signUpRequest);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


    @PostMapping("/users/login")
    @Operation(summary = "Connexion utilisateur", description = "Authentifie un utilisateur et retourne un token JWT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Connexion réussie"),
        @ApiResponse(responseCode = "401", description = "Identifiants invalides"),
        @ApiResponse(responseCode = "403", description = "Accès refusé")
    })
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest loginRequest, @RequestHeader(value = "X-Terminal-Id", required = false) String terminalId, @RequestHeader(value = "X-Terminal-Model", required = false) String terminalModel, @RequestHeader(value = "X-Terminal-Marque", required = false) String terminalMarque, HttpServletRequest request) throws AccessDeniedException {
        LoginResponse loginResponse = authService.login(loginRequest.getLogin(), loginRequest.getPassword(), terminalId, terminalModel, terminalMarque, AppUtils.getIpAddress(request));
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/scanneurs/login")
    @Operation(summary = "Connexion utilisateur scanneur", description = "Authentifie un utilisateur scanneur et retourne un token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connexion réussie"),
            @ApiResponse(responseCode = "401", description = "Identifiants invalides"),
            @ApiResponse(responseCode = "403", description = "Accès refusé")
    })
    public ResponseEntity<?> loginScanneur(@RequestBody @Valid AuthRequest loginRequest, @RequestHeader(value = "X-Terminal-Id", required = false) String terminalId, @RequestHeader(value = "X-Terminal-Model", required = false) String terminalModel, @RequestHeader(value = "X-Terminal-Marque", required = false) String terminalMarque, HttpServletRequest request) throws AccessDeniedException {
        LoginScanneurResponse loginScanneurResponse = authService.loginScanneur(loginRequest.getLogin(), loginRequest.getPassword(), terminalId, terminalModel, terminalMarque, AppUtils.getIpAddress(request));
        return new ResponseEntity<>(loginScanneurResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) throws AccessDeniedException {
        LoginResponse loginResponse = authService.refreshToken(refreshTokenRequest.getRefreshToken());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
    @PostMapping("/refresh/scanneur")
    public ResponseEntity<?> refreshTokenScanneur(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) throws AccessDeniedException {
        LoginScanneurResponse loginResponse = authService.refreshTokenScanneur(refreshTokenRequest.getRefreshToken());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }


    @PostMapping("/users/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid UpdateUserPasswordRequest updateUserPasswordRequest) {
        authService.updatePassword(updateUserPasswordRequest);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/scanneurs/update-password")
    public ResponseEntity<?> updatePasswordScanneur(@RequestBody @Valid UpdateUserPasswordRequest updateUserPasswordRequest) {
        authService.updatePasswordScanneur(updateUserPasswordRequest);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/users/update-password-forget")
    public ResponseEntity<?> updatePasswordForget(@RequestBody @Valid UpdateUserPasswordForgetRequest updateUserPasswordForgetRequest) throws AccessDeniedException {
        authService.updatePasswordForget(updateUserPasswordForgetRequest);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @PostMapping("/scanneurs/update-password-forget")
    public ResponseEntity<?> updatePasswordForgetScanneur(@RequestBody @Valid UpdateUserPasswordForgetRequest updateUserPasswordForgetRequest) throws AccessDeniedException {
        authService.updatePasswordForgetScanneur(updateUserPasswordForgetRequest);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/users/userconnected")
    public ResponseEntity<?> getUserConnected() {
        return new ResponseEntity<>(authService.getUserConnected(), HttpStatus.OK);
    }
    @GetMapping("/scanneurs/userconnected")
    public ResponseEntity<?> getScannerConnected() {
        return new ResponseEntity<>(authService.getScannerConnected(), HttpStatus.OK);
    }
}

