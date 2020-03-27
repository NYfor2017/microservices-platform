package com.ny.nycommoncore.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体绑定spring security
 *
 * @author N.Y
 * @date 2020-03-27 14:50
 */
public class LoginAppUser extends SysUser implements SocialUserDetails {
    private static final long serialVersionUID = 1L;

    private Set<String> permissions;

    /**
     * 权限重写
     * @return
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collection = new HashSet<>();
        if(!CollectionUtils.isEmpty(super.getRoles())){
            super.getRoles().parallelStream().forEach(role->collection.add(new SimpleGrantedAuthority(role.getCode())));
        }
        return collection;
    }

    @Override
    public String getUserId() {
        return getOpenId();
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
        return getEnabled();
    }
}
