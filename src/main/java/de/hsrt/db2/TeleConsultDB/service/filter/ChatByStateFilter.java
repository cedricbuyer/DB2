package de.hsrt.db2.TeleConsultDB.service.filters;

import de.hsrt.db2.TeleConsultDB.model.Chat;
import de.hsrt.db2.TeleConsultDB.enums.ChatState;

/**
 * Filters chats by their current {@link ChatState}.
 */
public record ChatByStateFilter(ChatState state) implements ChatFilter {
    @Override
    public boolean matches(Chat chat) {
        return chat.getChatState() == state;
    }
}