package de.hsrt.db2.TeleKlinikDB.commands.message;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.model.Message;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record DeleteMsg (
        @Getter UUID messageID
) implements MsgCommand {
    @Override
    public void execute(TeleKlinikContext ctx) {
        Optional<Message> msg = ctx.getMessageRepo().findById(messageID);

        if (msg.isEmpty()) {
            throw new NoSuchElementException("Message with ID " + messageID + " not found!");
        }

        ctx.getMessageRepo().delete(msg.get());
    }
}
