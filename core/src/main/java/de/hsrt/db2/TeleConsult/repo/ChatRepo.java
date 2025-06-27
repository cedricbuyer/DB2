package de.hsrt.db2.TeleConsult.repo;

import de.hsrt.db2.TeleConsult.enums.ChatState;
import de.hsrt.db2.TeleConsult.model.Chat;
import de.hsrt.db2.TeleConsult.model.GP;
import de.hsrt.db2.TeleConsult.model.Patient;

import java.util.List;

public interface ChatRepo extends BaseRepository<Chat> {
    List<Chat> findByChatStateAndGp(ChatState chatState, GP gpId);
    List<Chat> findByChatStateAndPatient(ChatState chatState, Patient patientId);
}
