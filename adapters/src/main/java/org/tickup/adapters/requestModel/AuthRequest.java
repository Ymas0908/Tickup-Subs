package org.tickup.adapters.requestModel;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotNull(message = "Le login est obligatoire")
    @NotEmpty(message = "Le login est obligatoire")
    private String login;
    @NotNull(message = "Le mot de passe est obligatoire")
    @NotEmpty(message = "Le mot de passe est obligatoire")
    private String password;

}
