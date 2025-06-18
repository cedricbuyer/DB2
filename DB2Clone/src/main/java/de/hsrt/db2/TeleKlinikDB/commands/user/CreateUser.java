package de.hsrt.db2.TeleKlinikDB.commands.user;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.model.User;
import lombok.Getter;

import java.sql.Date;

public record CreateUser (
        @Getter String name,
        @Getter String lastname,
        @Getter String password,
        @Getter String gender,
        @Getter Date birthdate
) implements UserCommand {
    @Override
    public void execute(TeleKlinikContext ctx) {
        User user = new User();
        user.setName(name);
        user.setLastname(lastname);
        user.setPassword(password);
        user.setGender(gender);
        user.setBirthdate(birthdate);
        ctx.getUserRepo().save(user);
    }
}
