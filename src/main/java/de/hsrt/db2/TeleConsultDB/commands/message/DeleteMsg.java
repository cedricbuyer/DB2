package de.hsrt.db2.TeleConsultDB.commands.message;

import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommand;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommandResult;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultContext;
import de.hsrt.db2.TeleConsultDB.model.Message;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record DeleteMsg (
        @Getter UUID messageID
) implements TeleConsultCommand {
    @Override
    public TeleConsultCommandResult execute(TeleConsultContext ctx) {
        Optional<Message> msg = ctx.getMessageRepo().findById(messageID);

        if (msg.isEmpty()) {
            throw new NoSuchElementException("Message with ID " + messageID + " not found!");
        }

        ctx.getMessageRepo().delete(msg.get());

        return TeleConsultCommandResult.emptyResult();
    }
}
