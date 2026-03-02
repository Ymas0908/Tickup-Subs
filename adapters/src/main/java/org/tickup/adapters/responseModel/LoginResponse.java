package org.tickup.adapters.responseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private int expiresIn;
    @JsonProperty("isFisrtConnection")
    private boolean isFisrtConnection;
    private String refUsager;


}
