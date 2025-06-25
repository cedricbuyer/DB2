package de.hsrt.db2.TeleConsultCore.model;

import de.hsrt.db2.TeleConsultCore.enums.MessageState;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.sql.Date;
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
public class Message {
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private User sender;

    @Getter
    @Setter
    private Chat chat;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private Blob attachment;

    @Getter
    @Setter
    private MessageState state;
}
