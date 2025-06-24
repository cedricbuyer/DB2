package de.hsrt.db2.TeleConsultDB.repo;

import de.hsrt.db2.TeleConsultDB.enums.MessageState;
import de.hsrt.db2.TeleConsultDB.model.Chat;
import de.hsrt.db2.TeleConsultDB.model.Message;
import de.hsrt.db2.TeleConsultDB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.UUID;

/**
 * Repository for Messages
 * <p>
 *     IMPORTANT: Method names influence what the respective method does.
 * </p>
 *
 * @author Frederik Beimgraben
 * @see Message
 */
public interface MessageRepo extends JpaRepository<Message, UUID> {
    List<Message> findByChat(Chat chat);
    List<Message> findByChatAndSender(Chat chat, User sender);
    List<Message> findByChatAndStateAndSender(Chat chat, MessageState state, User sender);
}


