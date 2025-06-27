package de.hsrt.db2.TeleConsult.repo;

import de.hsrt.db2.TeleConsult.model.GP;
import de.hsrt.db2.TeleConsult.model.Patient;
import de.hsrt.db2.TeleConsult.model.User;
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
public interface UserJpaRepo<T extends User> extends JpaRepository<T, UUID>, UserRepo<T> {
    List<T> findByNameContainsOrLastnameContains(String name, String lastname);
}