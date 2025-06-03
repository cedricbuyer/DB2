package de.hsrt.mis.IdentityManagementAL.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/* In myUserDetails, the inherited method returns an UserDetails object, so we need to create a class which
 * implements this, so we will need to define here a constructor that return the UserDetails object
 */
public class UserPrincipal implements UserDetails { // This refers to a user who is trying to login
    private final UserEntity user;

    public UserPrincipal(UserEntity user) {
        this.user = user;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This is a static object
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // IMPORTANT: FIXME: CONFIGURE THESE INHERITED METHODS BELOW IN PRODUCTION

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
}