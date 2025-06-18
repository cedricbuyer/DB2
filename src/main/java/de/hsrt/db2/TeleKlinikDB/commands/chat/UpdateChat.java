package de.hsrt.db2.TeleKlinikDB.commands.chat;

import de.hsrt.db2.TeleKlinikDB.model.Chat;
import de.hsrt.db2.TeleKlinikDB.model.ChatState;
import de.hsrt.db2.TeleKlinikDB.model.GP;
import de.hsrt.db2.TeleKlinikDB.model.Patient;
import de.hsrt.db2.TeleKlinikDB.repo.ChatRepo;
import de.hsrt.db2.TeleKlinikDB.repo.UserRepo;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class UpdateChat implements ChatCommand {
    @Getter
    private final UUID chatID;

    @Getter
    private final ChatState chatState;

    public UpdateChat(UUID chatID, ChatState chatState) {
        this.chatID = chatID;
        this.chatState = chatState;
    }

    @Override
    public void execute(ChatRepo chatRepo, UserRepo<GP> gpRepo, UserRepo<Patient> patientRepo) {
        Optional<Chat> chat = chatRepo.findById(this.chatID);

        if (chat.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + this.chatID + " not found!");
        }

        // FIXME: Does this work?
        chat.get().setChatState(chatState);
        chatRepo.save(chat.get());
    }
}
