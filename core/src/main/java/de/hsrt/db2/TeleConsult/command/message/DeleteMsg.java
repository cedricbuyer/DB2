package de.hsrt.db2.TeleConsult.command.message;

import de.hsrt.db2.TeleConsult.model.Message;
import de.hsrt.db2.TeleConsult.repo.RepoContext;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record DeleteMsg (
        @Getter UUID messageID
) implements MessageCommand {
    @Override
    public Message execute(RepoContext ctx) {
        Optional<Message> msg = ctx.getMessageRepo().findById(messageID);

        if (msg.isEmpty()) {
            throw new NoSuchElementException("Message with ID " + messageID + " not found!");
        }

        ctx.getMessageRepo().delete(msg.get());

        return null;
    }
}
