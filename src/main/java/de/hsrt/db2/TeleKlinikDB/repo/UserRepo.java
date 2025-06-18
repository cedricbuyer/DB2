package de.hsrt.db2.TeleKlinikDB.repo;

import de.hsrt.db2.TeleKlinikDB.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// In jpa, to connect to a db, we need to create a class which represents the table
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    // UserEntity findByUsername(String username);
}


