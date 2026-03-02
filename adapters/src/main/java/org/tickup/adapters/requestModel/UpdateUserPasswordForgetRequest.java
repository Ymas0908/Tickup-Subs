package org.tickup.adapters.requestModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserPasswordForgetRequest {
	private String login;
	private String newPassword;
	private String confirmNewPassword;
}
