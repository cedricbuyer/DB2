package de.hsrt.db2.TeleKlinikDB.commands.user;

import lombok.Getter;

import java.sql.Date;
import java.util.UUID;

public class CreateUser extends UserCommand {
    @Getter
    private final UUID userID;

    @Getter
    private final String name;

    @Getter
    private final String lastname;

    @Getter
    private final String password;

    @Getter
    private final String gender;

    @Getter
    private final Date birthdate;

    public CreateUser(UUID userID, String name, String lastname, String password, String gender, Date birthdate) {
        this.userID = userID;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.gender = gender;
        this.birthdate = birthdate;
    }
}
