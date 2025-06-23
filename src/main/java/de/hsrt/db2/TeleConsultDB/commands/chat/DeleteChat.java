package de.hsrt.db2.TeleConsultDB.commands.chat;

import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommand;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommandResult;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultContext;
import de.hsrt.db2.TeleConsultDB.model.Chat;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record DeleteChat (
        @Getter UUID chatID
) implements TeleConsultCommand {
    @Override
    public TeleConsultCommandResult execute(TeleConsultContext ctx) {
        // Get Chat to delete
        Optional<Chat> chat = ctx.getChatRepo().findById(chatID);

        if (chat.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + chatID + " not found!");
        }

        ctx.getChatRepo().delete(chat.get());

        return TeleConsultCommandResult.emptyResult();
    }
}
