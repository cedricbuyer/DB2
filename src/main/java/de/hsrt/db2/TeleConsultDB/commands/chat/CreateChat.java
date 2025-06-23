package de.hsrt.db2.TeleConsultDB.commands.chat;

import de.hsrt.db2.TeleConsultDB.commands.DataBaseContext;
import de.hsrt.db2.TeleConsultDB.commands.user.UserCommand;
import de.hsrt.db2.TeleConsultDB.enums.ChatState;
import de.hsrt.db2.TeleConsultDB.model.*;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record CreateChat (
        @Getter UUID gpID,
        @Getter UUID patientID
) implements ChatCommand {
    @Override
    public Chat execute(DataBaseContext ctx) {
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

        return ctx.getChatRepo().save(newChat);
    }
}
