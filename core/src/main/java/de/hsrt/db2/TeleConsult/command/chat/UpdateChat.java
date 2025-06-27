package de.hsrt.db2.TeleConsult.command.chat;

import de.hsrt.db2.TeleConsult.model.Chat;
import de.hsrt.db2.TeleConsult.repo.RepoContext;
import de.hsrt.db2.TeleConsult.enums.ChatState;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record UpdateChat (
        @Getter UUID chatID,
        @Getter ChatState chatState
) implements ChatCommand {
    @Override
    public Chat execute(RepoContext ctx) {
        Optional<Chat> chatOptional = ctx.getChatRepo().findById(chatID);

        if (chatOptional.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + chatID + " not found!");
        }

        Chat chat = chatOptional.get();
        chat.setChatState(chatState);

        return ctx.getChatRepo().save(chat);
    }
}
