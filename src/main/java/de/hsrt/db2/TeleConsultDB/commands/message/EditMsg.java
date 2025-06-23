package de.hsrt.db2.TeleConsultDB.commands.message;

import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommand;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommandResult;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultContext;
import de.hsrt.db2.TeleConsultDB.model.Message;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record EditMsg (
        @Getter UUID messageID,
        @Getter String text
) implements TeleConsultCommand {
    public EditMsg {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty!");
        }
    }

    @Override
    public TeleConsultCommandResult execute(TeleConsultContext ctx) {
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

        return TeleConsultCommandResult.emptyResult();
    }
}
