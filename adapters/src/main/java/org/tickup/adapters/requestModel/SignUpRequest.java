package org.tickup.adapters.requestModel;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tickup.domain.models.enums.Role;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
	@NotNull(message = "Le login est obligatoire")
	@NotEmpty(message = "Le login est obligatoire")
	@Size(min = 3, max = 20, message = "Le login doit contenir entre 3 et 20 caracteres")
	private String login;
	@NotNull(message = "Le mot de passe est obligatoire")
	@NotEmpty(message = "Le mot de passe est obligatoire")
	private String password;
	@NotNull(message = "Le nom est obligatoire")
	@NotEmpty(message = "Le nom est obligatoire")
	private String nom;
	@NotNull(message = "Le prenom est obligatoire")
	@NotEmpty(message = "Le prenom est obligatoire")
	private String prenom;
	//	@NotNull(message = "La reference de l'abonnement est obligatoire")
//	@NotEmpty(message = "La reference de l'abonnement est obligatoire")
	@NotNull(message = "Le role est obligatoire")
	private Role role;
	private String refUsager;
}
