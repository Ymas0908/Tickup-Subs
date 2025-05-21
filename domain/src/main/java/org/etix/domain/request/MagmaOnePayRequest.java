package org.etix.domain.request;

public class MagmaOnePayRequest {

    private String merchantTransactionId;    // Identifiant unique du commerçant pour la transaction
    private int amount;                      // Montant du paiement
    private String currency;                 // Devise (ex: XOF)
    private String description;              // Description de la transaction (optionnel)
    private String payee;                    // Numéro de téléphone du bénéficiaire
    private String payeeFirstName;           // Prénom du bénéficiaire
    private String payeeLastName;            // Nom de famille du bénéficiaire
    private String channel;                  // Canal utilisé (ex: mobile_money)
    private String webhookUrl;               // URL de webhook (optionnel)
    private String successUrl;               // URL de redirection en cas de succès
    private String errorUrl;                 // URL de redirection en cas d’erreur
    private String customField;

    private String callbackUrl;



    // Champ personnalisé

    // Constructeur par défaut
    public MagmaOnePayRequest() {
    }

    public MagmaOnePayRequest(String merchantTransactionId, int amount, String currency, String description, String payee, String payeeFirstName, String payeeLastName, String channel, String webhookUrl, String successUrl, String errorUrl, String customField, String callbackUrl) {
        this.merchantTransactionId = merchantTransactionId;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.payee = payee;
        this.payeeFirstName = payeeFirstName;
        this.payeeLastName = payeeLastName;
        this.channel = channel;
        this.webhookUrl = webhookUrl;
        this.successUrl = successUrl;
        this.errorUrl = errorUrl;
        this.customField = customField;
        this.callbackUrl = callbackUrl;
    }

    // Getters
    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public String getPayee() {
        return payee;
    }

    public String getPayeeFirstName() {
        return payeeFirstName;
    }

    public String getPayeeLastName() {
        return payeeLastName;
    }

    public String getChannel() {
        return channel;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public String getCustomField() {
        return customField;
    }

    // Setters
    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public void setPayeeFirstName(String payeeFirstName) {
        this.payeeFirstName = payeeFirstName;
    }

    public void setPayeeLastName(String payeeLastName) {
        this.payeeLastName = payeeLastName;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    public void setCustomField(String customField) {
        this.customField = customField;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
