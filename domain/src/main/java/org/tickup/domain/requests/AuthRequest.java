package org.tickup.domain.requests;

public class AuthRequest {
	private String clientId;
	private String clientSecret;
	private String nonce;

	public AuthRequest() {
	}

	private AuthRequest(Builder builder) {
		setClientId(builder.clientId);
		setClientSecret(builder.clientSecret);
		setNonce(builder.nonce);
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}


	public static final class Builder {
		private String clientId;
		private String clientSecret;
		private String nonce;

		public Builder() {
		}

		public Builder clientId(String clientId) {
			this.clientId = clientId;
			return this;
		}

		public Builder clientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
			return this;
		}

		public Builder nonce(String nonce) {
			this.nonce = nonce;
			return this;
		}

		public AuthRequest build() {
			return new AuthRequest(this);
		}
	}
}
