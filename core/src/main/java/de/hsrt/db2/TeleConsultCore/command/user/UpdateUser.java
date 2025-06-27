package de.hsrt.db2.TeleConsultCore.command.user;

import de.hsrt.db2.TeleConsultCore.command.RepoContext;
import de.hsrt.db2.TeleConsultCore.model.User;
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
    public User execute(RepoContext ctx) {
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
