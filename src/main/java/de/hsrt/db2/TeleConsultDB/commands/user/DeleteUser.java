package de.hsrt.db2.TeleConsultDB.commands.user;

import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommand;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommandResult;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultContext;
import de.hsrt.db2.TeleConsultDB.model.User;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record DeleteUser (
        @Getter UUID userID
) implements TeleConsultCommand {
    @Override
    public TeleConsultCommandResult execute(TeleConsultContext ctx) {
        Optional<User> user = ctx.getUserRepo().findById(userID);

        if (user.isEmpty()) {
            throw new NoSuchElementException("User with ID " + userID + " not found!");
        }

        ctx.getUserRepo().delete(user.get());

        return TeleConsultCommandResult.emptyResult();
    }
}
