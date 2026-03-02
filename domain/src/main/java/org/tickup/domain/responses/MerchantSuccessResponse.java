package org.tickup.domain.responses;


public class MerchantSuccessResponse {
	private String merchantId;
	private String terminalId;
	private String universalQRURL;
	private String decryptedMerchantSecureKey;
	private Boolean success;
	private String message;

	public MerchantSuccessResponse() {
	}

	private MerchantSuccessResponse(Builder builder) {
		setMerchantId(builder.merchantId);
		setTerminalId(builder.terminalId);
		setUniversalQRURL(builder.universalQRURL);
		setDecryptedMerchantSecureKey(builder.decryptedMerchantSecureKey);
		setSuccess(builder.success);
		setMessage(builder.message);
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getUniversalQRURL() {
		return universalQRURL;
	}

	public void setUniversalQRURL(String universalQRURL) {
		this.universalQRURL = universalQRURL;
	}

	public String getDecryptedMerchantSecureKey() {
		return decryptedMerchantSecureKey;
	}

	public void setDecryptedMerchantSecureKey(String decryptedMerchantSecureKey) {
		this.decryptedMerchantSecureKey = decryptedMerchantSecureKey;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public static final class Builder {
		private String merchantId;
		private String terminalId;
		private String universalQRURL;
		private String decryptedMerchantSecureKey;
		private Boolean success;
		private String message;

		public Builder() {
		}

		public Builder merchantId(String merchantId) {
			this.merchantId = merchantId;
			return this;
		}

		public Builder terminalId(String terminalId) {
			this.terminalId = terminalId;
			return this;
		}

		public Builder universalQRURL(String universalQRURL) {
			this.universalQRURL = universalQRURL;
			return this;
		}

		public Builder decryptedMerchantSecureKey(String decryptedMerchantSecureKey) {
			this.decryptedMerchantSecureKey = decryptedMerchantSecureKey;
			return this;
		}

		public Builder success(Boolean success) {
			this.success = success;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public MerchantSuccessResponse build() {
			return new MerchantSuccessResponse(this);
		}
	}

	@Override
	public String toString() {
		return "MerchantSuccessResponse{" +
				"merchantId='" + merchantId + '\'' +
				", terminalId='" + terminalId + '\'' +
				", universalQRURL='" + universalQRURL + '\'' +
				", decryptedMerchantSecureKey='" + decryptedMerchantSecureKey + '\'' +
				", success=" + success +
				", message='" + message + '\'' +
				'}';
	}
}
