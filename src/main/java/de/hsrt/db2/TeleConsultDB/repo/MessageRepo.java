package de.hsrt.db2.TeleConsultDB.repo;

import de.hsrt.db2.TeleConsultDB.model.Message;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository for Messages
 * <p>
 *     IMPORTANT: Method names influence what the respective method does.
 * </p>
 *
 * @author Frederik Beimgraben
 * @see Message
 * @see TeleConsultContext
 */
public interface MessageRepo extends JpaRepository<Message, UUID> { }


