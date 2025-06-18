package de.hsrt.db2.TeleKlinikDB.service;


import de.hsrt.db2.TeleKlinikDB.model.UserEntity;
import de.hsrt.db2.TeleKlinikDB.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    public List<UserEntity> getUsers() {
        return repo.findAll();
    }
}
