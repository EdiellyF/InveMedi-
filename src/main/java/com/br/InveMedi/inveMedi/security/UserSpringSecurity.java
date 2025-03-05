package com.br.InveMedi.inveMedi.security;

import com.br.InveMedi.inveMedi.models.enums.ProfileEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserSpringSecurity implements UserDetails {

    private Long id;
    private String email;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(Long id, String email, String password, Set<ProfileEnum> profileEnums) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = profileEnums.stream().map(profileEnum -> new SimpleGrantedAuthority(profileEnum.getDescription())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
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

    public boolean hasRole(ProfileEnum profileEnum) {
        return authorities.stream().anyMatch(authority -> authority.getAuthority().equals(profileEnum.getDescription()));
    }
}
