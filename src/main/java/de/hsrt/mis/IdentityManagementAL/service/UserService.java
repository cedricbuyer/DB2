package de.hsrt.mis.IdentityManagementAL.service;


import de.hsrt.mis.IdentityManagementAL.model.UserEntity;
import de.hsrt.mis.IdentityManagementAL.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserEntity register(UserEntity user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    public String login(UserEntity user) {
        Authentication authentication = authManager.authenticate
                (new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            // FIXME: We could do this method in this class but its better to do it in other one
            return jwtService.generateToken(user.getUsername());
        } else {
            return "Authentication Failed";
        }
    }

    public List<UserEntity> getUsers() {
        return repo.findAll();
    }
}
