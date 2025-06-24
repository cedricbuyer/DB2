package de.hsrt.db2.TeleConsultDB.commands.message;

import de.hsrt.db2.TeleConsultDB.commands.DataBaseContext;
import de.hsrt.db2.TeleConsultDB.enums.MessageState;
import de.hsrt.db2.TeleConsultDB.model.Message;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record MarkRead (
        @Getter UUID messageID
) implements MessageCommand {
    @Override
    public Message execute(DataBaseContext ctx) {
        Optional<Message> msgOptional = ctx.getMessageRepo().findById(messageID);

        if (msgOptional.isEmpty()) {
            throw new NoSuchElementException("Message with ID " + messageID + " not found!");
        }

        Message msg = msgOptional.get();
        msg.setState(MessageState.READ);

        return ctx.getMessageRepo().save(msg);
    }
}
