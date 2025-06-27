package de.hsrt.db2.TeleConsultCore.command.message;

import de.hsrt.db2.TeleConsultCore.model.Message;
import de.hsrt.db2.TeleConsultCore.command.RepoContext;
import de.hsrt.db2.TeleConsultCore.enums.MessageState;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record MarkRead (
        @Getter UUID messageID
) implements MessageCommand {
    @Override
    public Message execute(RepoContext ctx) {
        Optional<Message> msgOptional = ctx.getMessageRepo().findById(messageID);

        if (msgOptional.isEmpty()) {
            throw new NoSuchElementException("Message with ID " + messageID + " not found!");
        }

        Message msg = msgOptional.get();
        msg.setState(MessageState.READ);

        return ctx.getMessageRepo().save(msg);
    }
}
