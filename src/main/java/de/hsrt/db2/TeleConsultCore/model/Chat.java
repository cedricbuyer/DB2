package de.hsrt.db2.TeleConsultCore.model;

import de.hsrt.db2.TeleConsultCore.enums.ChatState;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


/**
 * Entity to store Chat information
 * <p>
 *     A `Chat` links exactly two Users – one GP and one Patient.
 *     It has a `chatState` of type `enum ChatState` (e.g., ACTIVE,
 *     ARCHIVED, ...).
 * <p/>
 *
 * @author Frederik Beimgraben
 */
public class Chat {
    @Getter
    private UUID id;

    @Getter
    @Setter
    private GP gp;

    @Getter
    @Setter
    private Patient patient;

    @Getter
    @Setter
    private ChatState chatState;
}
