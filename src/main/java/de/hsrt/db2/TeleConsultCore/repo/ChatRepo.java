package de.hsrt.db2.TeleConsultCore.repo;

import de.hsrt.db2.TeleConsultCore.enums.ChatState;
import de.hsrt.db2.TeleConsultCore.model.Chat;
import de.hsrt.db2.TeleConsultCore.model.GP;
import de.hsrt.db2.TeleConsultCore.model.Patient;

import java.util.List;
import java.util.UUID;

public interface ChatRepo extends BaseRepository<Chat> {
    List<Chat> findByChatStateAndGp(ChatState chatState, GP gpId);
    List<Chat> findByChatStateAndPatient(ChatState chatState, Patient patientId);
}
