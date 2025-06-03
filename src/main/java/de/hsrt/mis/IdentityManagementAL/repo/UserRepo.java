package de.hsrt.mis.IdentityManagementAL.repo;

import de.hsrt.mis.IdentityManagementAL.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// In jpa, to connect to a db, we need to create a class which represents the table
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}


