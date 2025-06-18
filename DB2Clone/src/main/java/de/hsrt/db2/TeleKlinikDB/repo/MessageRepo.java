package de.hsrt.db2.TeleKlinikDB.repo;

import de.hsrt.db2.TeleKlinikDB.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// In jpa, to connect to a db, we need to create a class which represents the table
public interface MessageRepo extends JpaRepository<Message, UUID> {

}


