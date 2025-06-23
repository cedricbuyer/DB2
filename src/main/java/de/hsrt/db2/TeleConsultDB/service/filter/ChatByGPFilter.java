package de.hsrt.db2.TeleConsultDB.service.filters;

import de.hsrt.db2.TeleConsultDB.model.Chat;
import java.util.UUID;

/**
 * Filters chats by the general practitioner's id.
 */
public record ChatByGPFilter(UUID gpId) implements de.hsrt.db2.TeleConsultDB.service.filters.ChatFilter {
    @Override
    public boolean matches(Chat chat) {
        return chat.getGp() != null &&
                chat.getGp().getId().equals(gpId);
    }
}