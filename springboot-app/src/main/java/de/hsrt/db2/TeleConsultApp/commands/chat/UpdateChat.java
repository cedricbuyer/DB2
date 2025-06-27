package de.hsrt.db2.TeleConsultDB.commands.chat;

import de.hsrt.db2.TeleConsultDB.repo.DataBaseContext;
import de.hsrt.db2.TeleConsultDB.model.Chat;
import de.hsrt.db2.TeleConsultDB.enums.ChatState;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record UpdateChat (
        @Getter UUID chatID,
        @Getter ChatState chatState
) implements ChatCommand {
    @Override
    public Chat execute(DataBaseContext ctx) {
        Optional<Chat> chatOptional = ctx.getChatRepo().findById(chatID);

        if (chatOptional.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + chatID + " not found!");
        }

        Chat chat = chatOptional.get();
        chat.setChatState(chatState);

        return ctx.getChatRepo().save(chat);
    }
}
