package de.hsrt.db2.TeleConsultCore.model;

import de.hsrt.db2.TeleConsultCore.enums.UserType;
import lombok.Getter;
import lombok.Setter;

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
 * @see GP
 * @see Patient
 */
public class User {
    @Getter
    @Setter
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