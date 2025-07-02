package de.hsrt.db2.TeleConsult.model;

import de.hsrt.db2.TeleConsult.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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

    public UserType getUserType() {
        if (this instanceof GP) {
            return UserType.GP;
        } else if (this instanceof Patient) {
            return UserType.PATIENT;
        } else {
            throw new IllegalArgumentException("Unknown user type");
        }
    }
}