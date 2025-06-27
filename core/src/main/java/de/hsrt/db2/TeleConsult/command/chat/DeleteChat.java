package de.hsrt.db2.TeleConsult.command.chat;

import de.hsrt.db2.TeleConsult.model.Chat;
import de.hsrt.db2.TeleConsult.repo.RepoContext;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record DeleteChat (
        UUID chatID
) implements ChatCommand {
    @Override
    public Chat execute(RepoContext ctx) {
        // Get Chat to delete
        Optional<Chat> chat = ctx.getChatRepo().findById(chatID);

        if (chat.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + chatID + " not found!");
        }

        ctx.getChatRepo().delete(chat.get());

        return null;
    }
}
