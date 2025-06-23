package de.hsrt.db2.TeleConsultDB.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.UUID;

import static jakarta.persistence.InheritanceType.JOINED;

/**
 * Entity to store Chat information
 * <p>
 *     A `Chat` links exactly two Users – one GP and one Patient.
 *     It has a `chatState` of type `enum ChatState` (e.g., ACTIVE,
 *     ARCHIVED, ...).
 * <p/>
 *
 * @author Frederik Beimgraben
 * @see GP
 * @see Patient
 * @see de.hsrt.db2.TeleConsultDB.commands.user
 * @see de.hsrt.db2.TeleConsultDB.repo.UserRepo
 */
@Entity
@Inheritance(strategy=JOINED)
public class User {
    @Id
    @Getter
    @GeneratedValue
    private UUID id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String gender;

    @Getter
    @Setter
    private Date birthdate;
}