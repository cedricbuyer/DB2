package de.hsrt.db2.TeleKlinikDB.commands.chat;

import de.hsrt.db2.TeleKlinikDB.model.*;
import de.hsrt.db2.TeleKlinikDB.repo.ChatRepo;
import de.hsrt.db2.TeleKlinikDB.repo.UserRepo;
import de.hsrt.db2.TeleKlinikDB.service.TeleConsultService;
import lombok.Getter;
import org.hibernate.exception.DataException;
import org.hibernate.query.results.MissingSqlSelectionException;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class CreateChat implements ChatCommand {
    @Getter
    private final UUID gpID;

    @Getter
    private final UUID patientID;

    public CreateChat(UUID gpID, UUID patientID) {
        if (gpID == null || patientID == null) {
            throw new IllegalArgumentException("userA_ID and userB_ID cannot be null");
        }

        this.gpID = gpID;
        this.patientID = patientID;
    }

    @Override
    public void execute(ChatRepo chatRepo, UserRepo<GP> gpRepo, UserRepo<Patient> patientRepo) {
        // Find GP and Patient
        Optional<GP> gp = gpRepo.findById(this.gpID);

        if (gp.isEmpty()) {
            throw new NoSuchElementException("Specified GP does not exist");
        }

        Optional<Patient> patient = patientRepo.findById(this.patientID);

        if (patient.isEmpty()) {
            throw new NoSuchElementException("Specified Patient does not exist");
        }

        // Create new Chat Object
        Chat newChat = new Chat();
        newChat.setGp(gp.get());
        newChat.setPatient(patient.get());
        newChat.setChatState(ChatState.ACTIVE);

        chatRepo.save(newChat);
    }
}
