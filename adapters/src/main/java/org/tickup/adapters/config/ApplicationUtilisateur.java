package org.tickup.adapters.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class ApplicationUtilisateur implements UserDetails {

    private static final long serialVersionUID = 8549458138380981159L;

    private Integer id;
    private String login;
    private String password;
    private List<SimpleGrantedAuthority> roles = new ArrayList<>();

    private ApplicationUtilisateur(Builder builder) {
        setId(builder.id);
        setLogin(builder.login);
        setPassword(builder.password);
        setRoles(builder.roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public static final class Builder {
        private Integer id;
        private String login;
        private String password;
        private List<SimpleGrantedAuthority> roles;

        public Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder roles(List<SimpleGrantedAuthority> roles) {
            this.roles = roles;
            return this;
        }


        public ApplicationUtilisateur build() {
            return new ApplicationUtilisateur(this);
        }
    }
}
