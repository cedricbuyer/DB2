package de.hsrt.db2.TeleKlinikDB.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String gender; // FIXME: Enum Type

    @Getter
    @Setter
    private Date birthdate;

    @Override
    public String toString() {
        return String.format(
                "UserEntity{id=%d, name='%s', lastname='%s', password='%s', gender='%s', birthdate='%s'}",
                id,
                name,
                password,
                gender,
                birthdate // FIXME: String conversion
        );
    }
}