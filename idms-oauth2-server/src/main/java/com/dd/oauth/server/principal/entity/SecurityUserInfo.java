package com.dd.oauth.server.principal.entity;

import com.dd.model.user.SysAccount;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Getter
@Setter
public class SecurityUserInfo extends SysAccount implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Set<GrantedAuthority> authorities;
    private  boolean accountNonExpired=true;
    private  boolean accountNonLocked=true;
    private  boolean credentialsNonExpired=true;
    private  boolean enabled=true;
    private String password;
    private String userName;

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return userName;
    }
}
