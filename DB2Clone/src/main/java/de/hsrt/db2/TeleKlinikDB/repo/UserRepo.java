package de.hsrt.db2.TeleKlinikDB.repo;

import de.hsrt.db2.TeleKlinikDB.model.GP;
import de.hsrt.db2.TeleKlinikDB.model.Patient;
import de.hsrt.db2.TeleKlinikDB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepo<T extends User> extends JpaRepository<T, UUID> {
    List<T> findUsersByNameAndLastname(String name, String lastname);
}