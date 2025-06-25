package de.hsrt.db2.TeleConsultCore.command.user;

import de.hsrt.db2.TeleConsultCore.command.RepoContext;
import de.hsrt.db2.TeleConsultCore.model.User;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record DeleteUser (
        @Getter UUID userID
) implements UserCommand {
    @Override
    public User execute(RepoContext ctx) {
        Optional<User> user = ctx.getUserRepo().findById(userID);

        if (user.isEmpty()) {
            throw new NoSuchElementException("User with ID " + userID + " not found!");
        }

        ctx.getUserRepo().delete(user.get());

        return null;
    }
}
