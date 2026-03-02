package org.tickup.domain.responses;

import java.time.LocalDateTime;

public class AuthResponse {
	private String accessToken;
	private String success;
	private LocalDateTime expiresAt;

	public AuthResponse() {
	}

	private AuthResponse(Builder builder) {
		setAccessToken(builder.accessToken);
		setSuccess(builder.success);
		setExpiresAt(builder.expiresAt);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}


	public static final class Builder {
		private String accessToken;
		private String success;
		private LocalDateTime expiresAt;

		public Builder() {
		}

		public Builder accessToken(String accessToken) {
			this.accessToken = accessToken;
			return this;
		}

		public Builder success(String success) {
			this.success = success;
			return this;
		}

		public Builder expiresAt(LocalDateTime expiresAt) {
			this.expiresAt = expiresAt;
			return this;
		}

		public AuthResponse build() {
			return new AuthResponse(this);
		}
	}
}
