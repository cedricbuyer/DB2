package de.hsrt.db2.TeleConsultDB.commands.chat;

import de.hsrt.db2.TeleConsultDB.commands.DataBaseCommand;
import de.hsrt.db2.TeleConsultDB.commands.DataBaseContext;
import de.hsrt.db2.TeleConsultDB.model.Chat;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record DeleteChat (
        @Getter UUID chatID
) implements ChatCommand {
    @Override
    public Chat execute(DataBaseContext ctx) {
        // Get Chat to delete
        Optional<Chat> chat = ctx.getChatRepo().findById(chatID);

        if (chat.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + chatID + " not found!");
        }

        ctx.getChatRepo().delete(chat.get());

        return null;
    }
}
