package de.hsrt.mis.TeleKlinikAPI.repo;

import de.hsrt.mis.TeleKlinikAPI.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// In jpa, to connect to a db, we need to create a class which represents the table
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}


