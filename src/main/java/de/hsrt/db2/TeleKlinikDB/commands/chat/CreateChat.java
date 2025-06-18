package de.hsrt.db2.TeleKlinikDB.commands.chat;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.model.*;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record CreateChat (
        @Getter UUID gpID,
        @Getter UUID patientID
) implements ChatCommand {
    @Override
    public void execute(TeleKlinikContext ctx) {
        // Find GP and Patient
        Optional<GP> gp = ctx.getGpUserRepo().findById(gpID);

        if (gp.isEmpty()) {
            throw new NoSuchElementException("Specified GP does not exist");
        }

        Optional<Patient> patient = ctx.getPatientUserRepo().findById(patientID);

        if (patient.isEmpty()) {
            throw new NoSuchElementException("Specified Patient does not exist");
        }

        // Create new Chat Object
        Chat newChat = new Chat();
        newChat.setGp(gp.get());
        newChat.setPatient(patient.get());
        newChat.setChatState(ChatState.ACTIVE);

        ctx.getChatRepo().save(newChat);
    }
}
