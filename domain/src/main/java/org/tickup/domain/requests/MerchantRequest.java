package org.tickup.domain.requests;


public class MerchantRequest {
	private Integer bankId;
	private String merchantName;
	private String fullName;
	private String address1;
	private String contactPersonMobile;
	private String email;
	private Integer categoryCodeId;
	private Integer countryId;
	private Integer stateId;
	private String acquirerMerchantId;
	private Boolean isAggregator;
	private Boolean merchantUserCanRefund;
	private Boolean payLinkEnabled;
	private Boolean isQRLink;
	private Boolean gimEnabled;
	private Boolean enableTermsAndConditions;

	public MerchantRequest() {
	}

	private MerchantRequest(Builder builder) {
		setBankId(builder.bankId);
		setMerchantName(builder.merchantName);
		setFullName(builder.fullName);
		setAddress1(builder.address1);
		setContactPersonMobile(builder.contactPersonMobile);
		setEmail(builder.email);
		setCategoryCodeId(builder.categoryCodeId);
		setCountryId(builder.countryId);
		setStateId(builder.stateId);
		setAcquirerMerchantId(builder.acquirerMerchantId);
		isAggregator = builder.isAggregator;
		setMerchantUserCanRefund(builder.merchantUserCanRefund);
		setPayLinkEnabled(builder.payLinkEnabled);
		isQRLink = builder.isQRLink;
		setGimEnabled(builder.gimEnabled);
		setEnableTermsAndConditions(builder.enableTermsAndConditions);
	}


	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getContactPersonMobile() {
		return contactPersonMobile;
	}

	public void setContactPersonMobile(String contactPersonMobile) {
		this.contactPersonMobile = contactPersonMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCategoryCodeId() {
		return categoryCodeId;
	}

	public void setCategoryCodeId(Integer categoryCodeId) {
		this.categoryCodeId = categoryCodeId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getAcquirerMerchantId() {
		return acquirerMerchantId;
	}

	public void setAcquirerMerchantId(String acquirerMerchantId) {
		this.acquirerMerchantId = acquirerMerchantId;
	}

	public Boolean getAggregator() {
		return isAggregator;
	}

	public void setAggregator(Boolean aggregator) {
		isAggregator = aggregator;
	}

	public Boolean getMerchantUserCanRefund() {
		return merchantUserCanRefund;
	}

	public void setMerchantUserCanRefund(Boolean merchantUserCanRefund) {
		this.merchantUserCanRefund = merchantUserCanRefund;
	}

	public Boolean getPayLinkEnabled() {
		return payLinkEnabled;
	}

	public void setPayLinkEnabled(Boolean payLinkEnabled) {
		this.payLinkEnabled = payLinkEnabled;
	}

	public Boolean getQRLink() {
		return isQRLink;
	}

	public void setQRLink(Boolean QRLink) {
		isQRLink = QRLink;
	}

	public Boolean getGimEnabled() {
		return gimEnabled;
	}

	public void setGimEnabled(Boolean gimEnabled) {
		this.gimEnabled = gimEnabled;
	}

	public Boolean getEnableTermsAndConditions() {
		return enableTermsAndConditions;
	}

	public void setEnableTermsAndConditions(Boolean enableTermsAndConditions) {
		this.enableTermsAndConditions = enableTermsAndConditions;
	}

	public static final class Builder {
		private Integer bankId;
		private String merchantName;
		private String fullName;
		private String address1;
		private String contactPersonMobile;
		private String email;
		private Integer categoryCodeId;
		private Integer countryId;
		private Integer stateId;
		private String acquirerMerchantId;
		private Boolean isAggregator;
		private Boolean merchantUserCanRefund;
		private Boolean payLinkEnabled;
		private Boolean isQRLink;
		private Boolean gimEnabled;
		private Boolean enableTermsAndConditions;

		public Builder() {
		}

		public Builder bankId(Integer val) {
			bankId = val;
			return this;
		}

		public Builder merchantName(String val) {
			merchantName = val;
			return this;
		}

		public Builder fullName(String val) {
			fullName = val;
			return this;
		}

		public Builder address1(String val) {
			address1 = val;
			return this;
		}

		public Builder contactPersonMobile(String val) {
			contactPersonMobile = val;
			return this;
		}

		public Builder email(String val) {
			email = val;
			return this;
		}

		public Builder categoryCodeId(Integer val) {
			categoryCodeId = val;
			return this;
		}

		public Builder countryId(Integer val) {
			countryId = val;
			return this;
		}

		public Builder stateId(Integer val) {
			stateId = val;
			return this;
		}

		public Builder acquirerMerchantId(String val) {
			acquirerMerchantId = val;
			return this;
		}

		public Builder isAggregator(Boolean val) {
			isAggregator = val;
			return this;
		}

		public Builder merchantUserCanRefund(Boolean val) {
			merchantUserCanRefund = val;
			return this;
		}

		public Builder payLinkEnabled(Boolean val) {
			payLinkEnabled = val;
			return this;
		}

		public Builder isQRLink(Boolean val) {
			isQRLink = val;
			return this;
		}

		public Builder gimEnabled(Boolean val) {
			gimEnabled = val;
			return this;
		}

		public Builder enableTermsAndConditions(Boolean val) {
			enableTermsAndConditions = val;
			return this;
		}

		public MerchantRequest build() {
			return new MerchantRequest(this);
		}
	}
}