package de.hsrt.db2.TeleKlinikDB.commands.user;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommand;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommandResult;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.model.User;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record DeleteUser (
        @Getter UUID userID
) implements TeleKlinikCommand {
    @Override
    public TeleKlinikCommandResult execute(TeleKlinikContext ctx) {
        Optional<User> user = ctx.getUserRepo().findById(userID);

        if (user.isEmpty()) {
            throw new NoSuchElementException("User with ID " + userID + " not found!");
        }

        ctx.getUserRepo().delete(user.get());

        return TeleKlinikCommandResult.emptyResult();
    }
}
