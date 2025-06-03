package de.hsrt.mis.IdentityManagementAL.service;


import de.hsrt.mis.IdentityManagementAL.model.UserPrincipal;
import de.hsrt.mis.IdentityManagementAL.model.UserEntity;
import de.hsrt.mis.IdentityManagementAL.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/* We want to use our own userDetailsService, so we have to implement UserDetailsService, so that spring injects this one.
 * this is used when setting the auth provider, as we need to set the userDetails the provider checks
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = repo.findByUsername(username);

        if(user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }

        /* we are using UserPrincipal(user) because we want to map fields, add extra logic and etc, so
         * in our UserPrincipal we must implement UserDetails because spring works with it
         */
        return new UserPrincipal(user);
    }
}