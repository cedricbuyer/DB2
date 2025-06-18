package de.hsrt.db2.TeleKlinikDB.model;

import de.hsrt.db2.TeleKlinikDB.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.UUID;

import static jakarta.persistence.InheritanceType.JOINED;

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
    @Enumerated(EnumType.STRING)
    private Gender gender; // FIXME: Enum Type

    @Getter
    @Setter
    private Date birthdate;

    @Override
    public String toString() {
        return String.format(
                "User{id=%s, name='%s', lastname='%s', gender='%s', birthdate='%s'}",
                id,
                name,
                lastname,
                gender,
                birthdate
        );
    }
}