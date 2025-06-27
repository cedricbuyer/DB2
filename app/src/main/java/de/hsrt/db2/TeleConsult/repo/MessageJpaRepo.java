package de.hsrt.db2.TeleConsult.repo;

import de.hsrt.db2.TeleConsult.enums.MessageState;
import de.hsrt.db2.TeleConsult.model.Message;
import de.hsrt.db2.TeleConsult.model.Chat;
import de.hsrt.db2.TeleConsult.model.User;
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
 */
public interface MessageJpaRepo extends JpaRepository<Message, UUID>, MessageRepo {
    List<Message> findByChat(Chat chat);
    List<Message> findByChatAndStateAndSender(Chat chat, MessageState state, User sender);
}


