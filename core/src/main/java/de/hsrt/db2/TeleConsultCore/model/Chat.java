package de.hsrt.db2.TeleConsultDB.model;

import de.hsrt.db2.TeleConsultCore.enums.ChatState;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

/**
 * Entity to store Chat information
 * <p>
 *     A `Chat` links exactly two Users – one GP and one Patient.
 *     It has a `chatState` of type `enum ChatState` (e.g., ACTIVE,
 *     ARCHIVED, ...).
 * <p/>
 *
 * @author Frederik Beimgraben
 * @see de.hsrt.db2.TeleConsultDB.commands.chat
 * @see de.hsrt.db2.TeleConsultDB.repo.ChatRepo
 */
@Entity
public class Chat {
    @Id
    @Getter
    @GeneratedValue
    private UUID id;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = REMOVE)
    private GP gp;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = REMOVE)
    private Patient patient;

    @Getter
    @Setter
    private ChatState chatState;
}
