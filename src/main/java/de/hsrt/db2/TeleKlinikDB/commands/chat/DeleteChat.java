package de.hsrt.db2.TeleKlinikDB.commands.chat;

import de.hsrt.db2.TeleKlinikDB.model.Chat;
import de.hsrt.db2.TeleKlinikDB.model.GP;
import de.hsrt.db2.TeleKlinikDB.model.Patient;
import de.hsrt.db2.TeleKlinikDB.repo.ChatRepo;
import de.hsrt.db2.TeleKlinikDB.repo.UserRepo;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class DeleteChat implements ChatCommand {
    @Getter
    private final UUID chatID;

    public DeleteChat(UUID chatID) {
        this.chatID = chatID;
    }

    @Override
    public void execute(ChatRepo chatRepo, UserRepo<GP> gpRepo, UserRepo<Patient> patientRepo) {
        // Get Chat to delete
        Optional<Chat> chat = chatRepo.findById(this.chatID);

        if (chat.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + this.chatID + " not found!");
        }

        chatRepo.delete(chat.get());
    }
}
