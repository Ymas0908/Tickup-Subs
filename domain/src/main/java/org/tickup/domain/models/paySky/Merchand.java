//package org.tickup.domain.models.paySky;
//
//import com.itcentrex.domain.models.Utilisateur;
//
//public class Merchand {
//	private Integer id;
//	private  String refMerchant;
//
//	private String bankMerchantAdminUserName;
//	private String merchantName;
//	private Integer bankId;
//
//	private String fullName;
//	private String address1;
//	private String contactPersonMobile;
//	private String email;
//	private Integer categoryCodeId;
//	private Integer countryId;
//	private Integer stateId;
//	private String acquirerMerchantId;
//	private Boolean isAggregator;
//	private Boolean merchantUserCanRefund;
//	private Boolean payLinkEnabled;
//	private Boolean isQRLink;
//	private Boolean gimEnabled;
//	private Boolean enableTermsAndConditions;
//	private String merchantId;
//	private String terminalId;
//	private String decryptedMerchantSecureKey;
//
//	private Merchand(Builder builder) {
//		setId(builder.id);
//		setRefMerchant(builder.refMerchant);
//		setBankMerchantAdminUserName(builder.bankMerchantAdminUserName);
//		setMerchantName(builder.merchantName);
//		setBankId(builder.bankId);
//		setFullName(builder.fullName);
//		setAddress1(builder.address1);
//		setContactPersonMobile(builder.contactPersonMobile);
//		setEmail(builder.email);
//		setCategoryCodeId(builder.categoryCodeId);
//		setCountryId(builder.countryId);
//		setStateId(builder.stateId);
//		setAcquirerMerchantId(builder.acquirerMerchantId);
//		isAggregator = builder.isAggregator;
//		setMerchantUserCanRefund(builder.merchantUserCanRefund);
//		setPayLinkEnabled(builder.payLinkEnabled);
//		isQRLink = builder.isQRLink;
//		setGimEnabled(builder.gimEnabled);
//		setEnableTermsAndConditions(builder.enableTermsAndConditions);
//		setMerchantId(builder.merchantId);
//		setTerminalId(builder.terminalId);
//		setDecryptedMerchantSecureKey(builder.decryptedMerchantSecureKey);
//	}
//
//	public String getRefMerchant() {
//		return refMerchant;
//	}
//
//	public void setRefMerchant(String refMerchant) {
//		this.refMerchant = refMerchant;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public Integer getBankId() {
//		return bankId;
//	}
//
//	public void setBankId(Integer bankId) {
//		this.bankId = bankId;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getBankMerchantAdminUserName() {
//		return bankMerchantAdminUserName;
//	}
//
//	public void setBankMerchantAdminUserName(String bankMerchantAdminUserName) {
//		this.bankMerchantAdminUserName = bankMerchantAdminUserName;
//	}
//
//	public String getMerchantName() {
//		return merchantName;
//	}
//
//	public void setMerchantName(String merchantName) {
//		this.merchantName = merchantName;
//	}
//
//	public String getFullName() {
//		return fullName;
//	}
//
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//
//	public String getAddress1() {
//		return address1;
//	}
//
//	public void setAddress1(String address1) {
//		this.address1 = address1;
//	}
//
//	public String getContactPersonMobile() {
//		return contactPersonMobile;
//	}
//
//	public void setContactPersonMobile(String contactPersonMobile) {
//		this.contactPersonMobile = contactPersonMobile;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public Integer getCategoryCodeId() {
//		return categoryCodeId;
//	}
//
//	public void setCategoryCodeId(Integer categoryCodeId) {
//		this.categoryCodeId = categoryCodeId;
//	}
//
//	public Integer getCountryId() {
//		return countryId;
//	}
//
//	public void setCountryId(Integer countryId) {
//		this.countryId = countryId;
//	}
//
//	public Integer getStateId() {
//		return stateId;
//	}
//
//	public void setStateId(Integer stateId) {
//		this.stateId = stateId;
//	}
//
//
//
//	public String getAcquirerMerchantId() {
//		return acquirerMerchantId;
//	}
//
//	public void setAcquirerMerchantId(String acquirerMerchantId) {
//		this.acquirerMerchantId = acquirerMerchantId;
//	}
//
//	public Boolean getAggregator() {
//		return isAggregator;
//	}
//
//	public void setAggregator(Boolean aggregator) {
//		isAggregator = aggregator;
//	}
//
//	public Boolean getMerchantUserCanRefund() {
//		return merchantUserCanRefund;
//	}
//
//	public void setMerchantUserCanRefund(Boolean merchantUserCanRefund) {
//		this.merchantUserCanRefund = merchantUserCanRefund;
//	}
//
//	public Boolean getPayLinkEnabled() {
//		return payLinkEnabled;
//	}
//
//	public void setPayLinkEnabled(Boolean payLinkEnabled) {
//		this.payLinkEnabled = payLinkEnabled;
//	}
//
//	public Boolean getQRLink() {
//		return isQRLink;
//	}
//
//	public void setQRLink(Boolean QRLink) {
//		isQRLink = QRLink;
//	}
//
//	public Boolean getGimEnabled() {
//		return gimEnabled;
//	}
//
//	public void setGimEnabled(Boolean gimEnabled) {
//		this.gimEnabled = gimEnabled;
//	}
//
//	public Boolean getEnableTermsAndConditions() {
//		return enableTermsAndConditions;
//	}
//
//	public void setEnableTermsAndConditions(Boolean enableTermsAndConditions) {
//		this.enableTermsAndConditions = enableTermsAndConditions;
//	}
//
//	public String getMerchantId() {
//		return merchantId;
//	}
//
//	public void setMerchantId(String merchantId) {
//		this.merchantId = merchantId;
//	}
//
//	public String getTerminalId() {
//		return terminalId;
//	}
//
//	public void setTerminalId(String terminalId) {
//		this.terminalId = terminalId;
//	}
//
//	public String getDecryptedMerchantSecureKey() {
//		return decryptedMerchantSecureKey;
//	}
//
//	public void setDecryptedMerchantSecureKey(String decryptedMerchantSecureKey) {
//		this.decryptedMerchantSecureKey = decryptedMerchantSecureKey;
//	}
//
//	public static final class Builder {
//		private Integer id;
//		private String refMerchant;
//		private String bankMerchantAdminUserName;
//		private String merchantName;
//		private Integer bankId;
//		private String fullName;
//		private String address1;
//		private String contactPersonMobile;
//		private String email;
//		private Integer categoryCodeId;
//		private Integer countryId;
//		private Integer stateId;
//		private String acquirerMerchantId;
//		private Boolean isAggregator;
//		private Boolean merchantUserCanRefund;
//		private Boolean payLinkEnabled;
//		private Boolean isQRLink;
//		private Boolean gimEnabled;
//		private Boolean enableTermsAndConditions;
//		private String merchantId;
//		private String terminalId;
//		private String decryptedMerchantSecureKey;
//		private Utilisateur utilisateur;
//
//		public Builder() {
//		}
//
//		public Builder id(Integer val) {
//			id = val;
//			return this;
//		}
//
//		public Builder refMerchant(String val) {
//			refMerchant = val;
//			return this;
//		}
//
//		public Builder bankMerchantAdminUserName(String val) {
//			bankMerchantAdminUserName = val;
//			return this;
//		}
//
//		public Builder merchantName(String val) {
//			merchantName = val;
//			return this;
//		}
//
//		public Builder bankId(Integer val) {
//			bankId = val;
//			return this;
//		}
//
//		public Builder fullName(String val) {
//			fullName = val;
//			return this;
//		}
//
//		public Builder address1(String val) {
//			address1 = val;
//			return this;
//		}
//
//		public Builder contactPersonMobile(String val) {
//			contactPersonMobile = val;
//			return this;
//		}
//
//		public Builder email(String val) {
//			email = val;
//			return this;
//		}
//
//		public Builder categoryCodeId(Integer val) {
//			categoryCodeId = val;
//			return this;
//		}
//
//		public Builder countryId(Integer val) {
//			countryId = val;
//			return this;
//		}
//
//		public Builder stateId(Integer val) {
//			stateId = val;
//			return this;
//		}
//
//		public Builder acquirerMerchantId(String val) {
//			acquirerMerchantId = val;
//			return this;
//		}
//
//		public Builder isAggregator(Boolean val) {
//			isAggregator = val;
//			return this;
//		}
//
//		public Builder merchantUserCanRefund(Boolean val) {
//			merchantUserCanRefund = val;
//			return this;
//		}
//
//		public Builder payLinkEnabled(Boolean val) {
//			payLinkEnabled = val;
//			return this;
//		}
//
//		public Builder isQRLink(Boolean val) {
//			isQRLink = val;
//			return this;
//		}
//
//		public Builder gimEnabled(Boolean val) {
//			gimEnabled = val;
//			return this;
//		}
//
//		public Builder enableTermsAndConditions(Boolean val) {
//			enableTermsAndConditions = val;
//			return this;
//		}
//
//		public Builder merchantId(String val) {
//			merchantId = val;
//			return this;
//		}
//
//		public Builder terminalId(String val) {
//			terminalId = val;
//			return this;
//		}
//
//		public Builder decryptedMerchantSecureKey(String val) {
//			decryptedMerchantSecureKey = val;
//			return this;
//		}
//
//		public Builder utilisateur(Utilisateur val) {
//			utilisateur = val;
//			return this;
//		}
//
//		public Merchand build() {
//			return new Merchand(this);
//		}
//	}
//}