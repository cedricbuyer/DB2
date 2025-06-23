package de.hsrt.db2.TeleConsultDB.commands.chat;

import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommand;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommandResult;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultContext;
import de.hsrt.db2.TeleConsultDB.model.Chat;
import de.hsrt.db2.TeleConsultDB.enums.ChatState;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record UpdateChat (
        @Getter UUID chatID,
        @Getter ChatState chatState
) implements TeleConsultCommand {
    @Override
    public TeleConsultCommandResult execute(TeleConsultContext ctx) {
        Optional<Chat> chatOptional = ctx.getChatRepo().findById(chatID);

        if (chatOptional.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + chatID + " not found!");
        }

        Chat chat = chatOptional.get();

        // FIXME: Does this work?
        chat.setChatState(chatState);
        ctx.getChatRepo().save(chat);

        return TeleConsultCommandResult.emptyResult();
    }
}
