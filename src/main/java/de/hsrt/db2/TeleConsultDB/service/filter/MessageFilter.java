package de.hsrt.db2.TeleConsultDB.service.filters;

import de.hsrt.db2.TeleConsultDB.model.Message;

/**
 * Generic filter for {@link Message} instances.
 */
@FunctionalInterface
public interface MessageFilter {
    boolean matches(Message message);
}