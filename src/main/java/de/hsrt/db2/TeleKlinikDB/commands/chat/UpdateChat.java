package de.hsrt.db2.TeleKlinikDB.commands.chat;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommand;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommandResult;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.model.Chat;
import de.hsrt.db2.TeleKlinikDB.enums.ChatState;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record UpdateChat (
        @Getter UUID chatID,
        @Getter ChatState chatState
) implements TeleKlinikCommand {
    @Override
    public TeleKlinikCommandResult execute(TeleKlinikContext ctx) {
        Optional<Chat> chatOptional = ctx.getChatRepo().findById(chatID);

        if (chatOptional.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + chatID + " not found!");
        }

        Chat chat = chatOptional.get();

        // FIXME: Does this work?
        chat.setChatState(chatState);
        ctx.getChatRepo().save(chat);

        return TeleKlinikCommandResult.emptyResult();
    }
}
