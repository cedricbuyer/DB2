package de.hsrt.db2.TeleKlinikDB.commands.message;

import de.hsrt.db2.TeleKlinikDB.model.Chat;
import de.hsrt.db2.TeleKlinikDB.model.Message;
import de.hsrt.db2.TeleKlinikDB.model.User;
import de.hsrt.db2.TeleKlinikDB.repo.ChatRepo;
import de.hsrt.db2.TeleKlinikDB.repo.MessageRepo;
import de.hsrt.db2.TeleKlinikDB.repo.UserRepo;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class EditMsg implements MsgCommand {
    @Getter
    private final UUID messageID;

    @Getter
    private final String text;

    public EditMsg(UUID messageID, String text) {
        this.messageID = messageID;

        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty!");
        }

        this.text = text;
    }

    public void execute(ChatRepo chatRepo, UserRepo<User> userRepo, MessageRepo messageRepo) {
        Optional<Message> msg = messageRepo.findById(this.messageID);
        if (msg.isEmpty()) {
            throw new NoSuchElementException("Message with ID " + this.messageID + " not found!");
        }

        if (this.text == null || this.text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty!");
        }

        msg.get().setText(this.text);
        messageRepo.save(msg.get());
    }
}
