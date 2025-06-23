package de.hsrt.db2.TeleConsultDB.repo;

import de.hsrt.db2.TeleConsultDB.model.Chat;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository for Chats
 * <p>
 *     IMPORTANT: Method names influence what the respective method does.
 *     e.g.: <code>List< Chat > findChatsByPatient(Patient patient);</code>
 * </p>
 *
 * @author Frederik Beimgraben
 * @see Chat
 * @see TeleConsultContext
 */
public interface ChatRepo extends JpaRepository<Chat, UUID> { }


