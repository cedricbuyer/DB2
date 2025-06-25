package de.hsrt.db2.TeleConsultCore.command.chat;

import de.hsrt.db2.TeleConsultCore.model.Chat;
import de.hsrt.db2.TeleConsultCore.model.GP;
import de.hsrt.db2.TeleConsultCore.model.Patient;
import de.hsrt.db2.TeleConsultCore.command.RepoContext;
import de.hsrt.db2.TeleConsultCore.enums.ChatState;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record CreateChat (
        UUID gpID,
        UUID patientID
) implements ChatCommand {
    @Override
    public Chat execute(RepoContext ctx) {
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
