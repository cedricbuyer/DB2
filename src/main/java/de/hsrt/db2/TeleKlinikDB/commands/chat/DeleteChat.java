package de.hsrt.db2.TeleKlinikDB.commands.chat;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommandResult;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.model.Chat;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record DeleteChat (
        @Getter UUID chatID
) implements ChatCommand {
    @Override
    public TeleKlinikCommandResult execute(TeleKlinikContext ctx) {
        // Get Chat to delete
        Optional<Chat> chat = ctx.getChatRepo().findById(chatID);

        if (chat.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + chatID + " not found!");
        }

        ctx.getChatRepo().delete(chat.get());

        return TeleKlinikCommandResult.emptyResult();
    }
}
