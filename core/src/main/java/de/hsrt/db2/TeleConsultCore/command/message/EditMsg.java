package de.hsrt.db2.TeleConsultCore.command.message;

import de.hsrt.db2.TeleConsultCore.model.Message;
import de.hsrt.db2.TeleConsultCore.command.RepoContext;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record EditMsg (
        @Getter UUID messageID,
        @Getter String text
) implements MessageCommand {
    public EditMsg {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty!");
        }
    }

    @Override
    public Message execute(RepoContext ctx) {
        Optional<Message> msgOptional = ctx.getMessageRepo().findById(messageID);

        if (msgOptional.isEmpty()) {
            throw new NoSuchElementException("Message with ID " + messageID + " not found!");
        }

        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty!");
        }

        Message msg = msgOptional.get();
        msg.setText(text);

        return ctx.getMessageRepo().save(msg);
    }
}
