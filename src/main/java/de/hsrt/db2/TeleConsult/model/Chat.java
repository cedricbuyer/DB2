package de.hsrt.db2.TeleConsult.model;

import de.hsrt.db2.TeleConsultCore.enums.ChatState;
import de.hsrt.db2.TeleConsult.repo.ChatJpaRepo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static jakarta.persistence.CascadeType.REMOVE;

/**
 * Entity to store Chat information
 * <p>
 *     A `Chat` links exactly two Users – one GP and one Patient.
 *     It has a `chatState` of type `enum ChatState` (e.g., ACTIVE,
 *     ARCHIVED, ...).
 * <p/>
 *
 * @author Frederik Beimgraben
 * @see ChatJpaRepo
 */
@Entity
public class Chat extends de.hsrt.db2.TeleConsultCore.model.Chat {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, cascade = REMOVE)
    private GP gp;

    @ManyToOne(optional = false, cascade = REMOVE)
    private Patient patient;

    private ChatState chatState;
}
