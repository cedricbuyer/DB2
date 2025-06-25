package de.hsrt.db2.TeleConsult.repo;

import de.hsrt.db2.TeleConsultCore.enums.ChatState;
import de.hsrt.db2.TeleConsultCore.model.Chat;
import de.hsrt.db2.TeleConsultCore.repo.ChatRepo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
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
 */
public interface ChatJpaRepo extends JpaRepository<Chat, UUID>, ChatRepo {
    List<Chat> findByChatStateAndPatientId(ChatState chatState, UUID patientId);
    List<Chat> findByChatStateAndGpId(ChatState chatState, UUID gpId);
}


