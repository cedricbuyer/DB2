package de.hsrt.db2.TeleConsult.model;

import de.hsrt.db2.TeleConsultCore.enums.MessageState;
import de.hsrt.db2.TeleConsult.repo.MessageJpaRepo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.sql.Date;
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
 * @see MessageJpaRepo
 */
@Entity
public class Message extends de.hsrt.db2.TeleConsultCore.model.Message {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, cascade = REMOVE)
    private User sender;

    @ManyToOne(optional = false, cascade = REMOVE)
    private Chat chat;

    private Date date;

    private String text;

    @Lob
    private Blob attachment;

    private MessageState state;
}
