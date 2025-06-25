package de.hsrt.db2.TeleConsult.model;

import de.hsrt.db2.TeleConsultCore.enums.UserType;
import de.hsrt.db2.TeleConsult.repo.UserJpaRepo;
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
 * @see UserJpaRepo
 */
@Entity
@Inheritance(strategy=JOINED)
public class User extends de.hsrt.db2.TeleConsultCore.model.User {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String lastname;

    private String gender;

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