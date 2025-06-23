package de.hsrt.db2.TeleConsultDB.service.filters;

import de.hsrt.db2.TeleConsultDB.model.Chat;
import java.util.UUID;

/**
 * Filters chats by the patient's id.
 */
public record ChatByPatientFilter(UUID patientId) implements ChatFilter {
    @Override
    public boolean matches(Chat chat) {
        return chat.getPatient() != null &&
                chat.getPatient().getId().equals(patientId);
    }
}