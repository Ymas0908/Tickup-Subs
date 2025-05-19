package org.etix.domain.request;

public class PaytechPaymentRequest {
    private String nomProduit;
    private int prixProduit;
    private String devise;
    private String descriptionProduit;

    private String refCommande;

    private String callbackUrl;

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(int prixProduit) {
        this.prixProduit = prixProduit;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public String getRefCommande() {
        return refCommande;
    }

    public void setRefCommande(String refCommande) {
        this.refCommande = refCommande;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public PaytechPaymentRequest() {
    }

}
