package org.tickup.domain.models;

import org.tickup.domain.models.enums.Role;

import java.time.LocalDateTime;

public class UtilisateurScanneur {
    private static final long serialVersionUID = 6839172631570706350L;
    private Integer id;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime dateDernConnexion;
    private LocalDateTime dateDernModification;
    private String login;
    private String password;
    private LocalDateTime datePasswordExpire;
    private Boolean statutActif;
    private Role role;
    private boolean isFisrtConnection;
    private String refScanneur;

    public UtilisateurScanneur() {
    }

    private UtilisateurScanneur(Builder builder) {
        setId(builder.id);
        setCreatedAt(builder.createdAt);
        setDeletedAt(builder.deletedAt);
        setDateDernConnexion(builder.dateDernConnexion);
        setDateDernModification(builder.dateDernModification);
        setLogin(builder.login);
        setPassword(builder.password);
        setDatePasswordExpire(builder.datePasswordExpire);
        setStatutActif(builder.statutActif);
        setRole(builder.role);
        setFisrtConnection(builder.isFisrtConnection);
        setRefScanneur(builder.refScanneur);
    }

    @Override
    public String toString() {
        return "UtilisateurScanneur{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                ", dateDernConnexion=" + dateDernConnexion +
                ", dateDernModification=" + dateDernModification +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", datePasswordExpire=" + datePasswordExpire +
                ", statutActif=" + statutActif +
                ", role=" + role +
                ", isFisrtConnection=" + isFisrtConnection +
                ", refScanneur='" + refScanneur + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getDateDernConnexion() {
        return dateDernConnexion;
    }

    public void setDateDernConnexion(LocalDateTime dateDernConnexion) {
        this.dateDernConnexion = dateDernConnexion;
    }

    public LocalDateTime getDateDernModification() {
        return dateDernModification;
    }

    public void setDateDernModification(LocalDateTime dateDernModification) {
        this.dateDernModification = dateDernModification;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDatePasswordExpire() {
        return datePasswordExpire;
    }

    public void setDatePasswordExpire(LocalDateTime datePasswordExpire) {
        this.datePasswordExpire = datePasswordExpire;
    }

    public Boolean getStatutActif() {
        return statutActif;
    }

    public void setStatutActif(Boolean statutActif) {
        this.statutActif = statutActif;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isFisrtConnection() {
        return isFisrtConnection;
    }

    public void setFisrtConnection(boolean fisrtConnection) {
        isFisrtConnection = fisrtConnection;
    }

    public String getRefScanneur() {
        return refScanneur;
    }

    public void setRefScanneur(String refScanneur) {
        this.refScanneur = refScanneur;
    }

    public static final class Builder {
        private Integer id;
        private LocalDateTime createdAt;
        private LocalDateTime deletedAt;
        private LocalDateTime dateDernConnexion;
        private LocalDateTime dateDernModification;
        private String login;
        private String password;
        private LocalDateTime datePasswordExpire;
        private Boolean statutActif;
        private Role role;
        private boolean isFisrtConnection;
        private String refScanneur;

        public Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder createdAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder deletedAt(LocalDateTime val) {
            deletedAt = val;
            return this;
        }

        public Builder dateDernConnexion(LocalDateTime val) {
            dateDernConnexion = val;
            return this;
        }

        public Builder dateDernModification(LocalDateTime val) {
            dateDernModification = val;
            return this;
        }

        public Builder login(String val) {
            login = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder datePasswordExpire(LocalDateTime val) {
            datePasswordExpire = val;
            return this;
        }

        public Builder statutActif(Boolean val) {
            statutActif = val;
            return this;
        }

        public Builder role(Role val) {
            role = val;
            return this;
        }

        public Builder isFisrtConnection(boolean val) {
            isFisrtConnection = val;
            return this;
        }

        public Builder refScanneur(String val) {
            refScanneur = val;
            return this;
        }

        public UtilisateurScanneur build() {
            return new UtilisateurScanneur(this);
        }
    }
}
