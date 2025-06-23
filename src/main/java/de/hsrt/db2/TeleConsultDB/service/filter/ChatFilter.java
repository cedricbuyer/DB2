package de.hsrt.db2.TeleConsultDB.service.filters;

import de.hsrt.db2.TeleConsultDB.model.Chat;

/**
 * Generic filter for {@link Chat} instances.
 * Implementations should define the filtering logic in {@link #matches(Chat)}.
 */
@FunctionalInterface
public interface ChatFilter {
    boolean matches(Chat chat);
}