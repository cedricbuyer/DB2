package de.hsrt.db2.TeleConsultDB.repo;

import de.hsrt.db2.TeleConsultDB.model.GP;
import de.hsrt.db2.TeleConsultDB.model.Patient;
import de.hsrt.db2.TeleConsultDB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for Chats
 * <p>
 *     IMPORTANT: Method names influence what the respective method does.
 * </p>
 *
 * @author Frederik Beimgraben
 * @see User
 * @see GP
 * @see Patient
 */
public interface UserRepo<T extends User> extends JpaRepository<T, UUID> {
    List<T> findByNameOrLastname(String name, String lastname);
}