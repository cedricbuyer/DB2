package de.hsrt.db2.TeleConsultDB.service.filters;

import de.hsrt.db2.TeleConsultDB.model.Message;
import java.util.UUID;

/**
 * Filters messages by the chat id they belong to.
 */
public record MessageByChatFilter(UUID chatId) implements de.hsrt.db2.TeleConsultDB.service.filters.MessageFilter {
    @Override
    public boolean matches(Message message) {
        return message.getChat() != null &&
                message.getChat().getId().equals(chatId);
    }
}