package de.hsrt.db2.TeleConsult.command.user;

import de.hsrt.db2.TeleConsult.enums.UserType;
import de.hsrt.db2.TeleConsult.model.GP;
import de.hsrt.db2.TeleConsult.model.Patient;
import de.hsrt.db2.TeleConsult.repo.RepoContext;
import de.hsrt.db2.TeleConsult.model.User;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record DeleteUser (
        @Getter UUID userID
) implements UserCommand {
    @Override
    public User execute(RepoContext ctx) {
        Optional<User> userOptional = ctx.getUserRepo().findById(userID);

        if (userOptional.isEmpty()) {
            throw new NoSuchElementException("User with ID " + userID + " not found!");
        }

        User user = userOptional.get();

        switch (user.getUserType()) {
            case GP -> ctx.getGpUserRepo().delete((GP)user);
            case PATIENT -> ctx.getPatientUserRepo().delete((Patient)user);
        }

        return null;
    }
}
