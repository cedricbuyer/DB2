package de.hsrt.db2.TeleConsultDB.repo;

import de.hsrt.db2.TeleConsultDB.model.GP;
import de.hsrt.db2.TeleConsultDB.model.Patient;
import de.hsrt.db2.TeleConsultDB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultContext;

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
 * @see TeleConsultContext
 */
public interface UserRepo<T extends User> extends JpaRepository<T, UUID> { }