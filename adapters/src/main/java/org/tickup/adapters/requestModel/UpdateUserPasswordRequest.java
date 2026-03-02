package org.tickup.adapters.requestModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserPasswordRequest {
    private String login;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
