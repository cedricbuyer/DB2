package de.hsrt.db2.TeleConsult.model;

import de.hsrt.db2.TeleConsult.enums.MessageState;
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
 */
@Entity
public class Message {
    @Id
    @Getter
    @GeneratedValue
    private UUID id;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = REMOVE)
    private User sender;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = REMOVE)
    private Chat chat;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    @Lob
    private Blob attachment;

    @Getter
    @Setter
    private MessageState state;
}
