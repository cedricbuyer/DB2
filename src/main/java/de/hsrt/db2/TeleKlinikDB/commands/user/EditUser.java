package de.hsrt.db2.TeleKlinikDB.commands.user;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommandResult;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.model.User;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record EditUser (
    @Getter UUID userID,
    @Getter String name,
    @Getter String lastname,
    @Getter String gender
) implements UserCommand {
    @Override
    public TeleKlinikCommandResult execute(TeleKlinikContext ctx) {
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

        ctx.getUserRepo().save(user);

        return TeleKlinikCommandResult.emptyResult();
    }
}
