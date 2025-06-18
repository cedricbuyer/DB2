package de.hsrt.db2.TeleKlinikDB.commands.message;

import de.hsrt.db2.TeleKlinikDB.model.Message;
import de.hsrt.db2.TeleKlinikDB.model.User;
import de.hsrt.db2.TeleKlinikDB.repo.ChatRepo;
import de.hsrt.db2.TeleKlinikDB.repo.MessageRepo;
import de.hsrt.db2.TeleKlinikDB.repo.UserRepo;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class DeleteMsg implements MsgCommand {
    @Getter
    private final UUID messageID;

    public DeleteMsg(UUID messageID) {
        this.messageID = messageID;
    }

    public void execute(ChatRepo chatRepo, UserRepo<User> userRepo, MessageRepo messageRepo) {
        Optional<Message> msg = messageRepo.findById(this.messageID);
        if (msg.isEmpty()) {
            throw new NoSuchElementException("Message with ID " + this.messageID + " not found!");
        }

        messageRepo.delete(msg.get());
    }
}
