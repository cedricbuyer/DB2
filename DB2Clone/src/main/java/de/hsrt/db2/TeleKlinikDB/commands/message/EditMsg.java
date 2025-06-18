package de.hsrt.db2.TeleKlinikDB.commands.message;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.model.Message;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record EditMsg (
        @Getter UUID messageID,
        @Getter String text
) implements MsgCommand {
    public EditMsg {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty!");
        }
    }

    @Override
    public void execute(TeleKlinikContext ctx) {
        Optional<Message> msgOptional = ctx.getMessageRepo().findById(messageID);

        if (msgOptional.isEmpty()) {
            throw new NoSuchElementException("Message with ID " + messageID + " not found!");
        }

        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty!");
        }

        Message msg = msgOptional.get();

        msg.setText(text);
        ctx.getMessageRepo().save(msg);
    }
}
