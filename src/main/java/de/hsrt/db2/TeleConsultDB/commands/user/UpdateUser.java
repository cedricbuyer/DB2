package de.hsrt.db2.TeleConsultDB.commands.user;

import de.hsrt.db2.TeleConsultDB.commands.DataBaseCommand;
import de.hsrt.db2.TeleConsultDB.commands.DataBaseContext;
import de.hsrt.db2.TeleConsultDB.model.User;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record UpdateUser(
    @Getter UUID userID,
    @Getter String name,
    @Getter String lastname,
    @Getter String gender
) implements UserCommand {
    @Override
    public User execute(DataBaseContext ctx) {
        Optional<User> userOptional = ctx.getUserRepo().findById(userID);

        if (userOptional.isEmpty()) {
            throw new NoSuchElementException("User with ID " + userID + " not found!");
        }

        User user = userOptional.get();

        if (name != null && !name.isEmpty()) {
            user.setName(name);
        }

        if (lastname != null && !lastname.isEmpty()) {
            user.setLastname(lastname);
        }

        if (gender != null && !gender.isEmpty()) {
            user.setGender(gender);
        }

        return ctx.getUserRepo().save(user);
    }
}
