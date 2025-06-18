package de.hsrt.db2.TeleKlinikDB.repo;

import de.hsrt.db2.TeleKlinikDB.model.Chat;
import de.hsrt.db2.TeleKlinikDB.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

// In jpa, to connect to a db, we need to create a class which represents the table
public interface ChatRepo extends JpaRepository<Chat, UUID> {
    List<Chat> findChatsByPatient(Patient patient);
}


