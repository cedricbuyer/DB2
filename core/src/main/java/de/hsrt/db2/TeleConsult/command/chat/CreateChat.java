package de.hsrt.db2.TeleConsult.command.chat;

import de.hsrt.db2.TeleConsult.model.Chat;
import de.hsrt.db2.TeleConsult.model.GP;
import de.hsrt.db2.TeleConsult.model.Patient;
import de.hsrt.db2.TeleConsult.repo.RepoContext;
import de.hsrt.db2.TeleConsult.enums.ChatState;

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
        Optional<GP> gpOptional = ctx.getGpUserRepo().findById(gpID);

        if (gpOptional.isEmpty()) {
            throw new NoSuchElementException("Specified GP does not exist");
        }

        Optional<Patient> patientOptional = ctx.getPatientUserRepo().findById(patientID);

        if (patientOptional.isEmpty()) {
            throw new NoSuchElementException("Specified Patient does not exist");
        }

        GP gp = gpOptional.get();
        Patient patient = patientOptional.get();

        // Create new Chat Object
        Chat newChat = new Chat();
        newChat.setChatState(ChatState.ACTIVE);
        newChat.setGp(gp);
        newChat.setPatient(patient);

        return ctx.getChatRepo().save(newChat);
    }
}
