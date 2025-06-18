package de.hsrt.db2.TeleKlinikDB.commands.user;

import lombok.Getter;

import java.util.UUID;

public class EditUser extends UserCommand {
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

    public EditUser(UUID userID, String name, String lastname, String password, String gender) {
        this.userID = userID;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.gender = gender;
    }
}
