package de.hsrt.db2.TeleKlinikDB.commands.user;

import lombok.Getter;

import java.util.UUID;

public class DeleteUser extends UserCommand {
    @Getter
    private final UUID userId;

    public DeleteUser(UUID userId) {
        this.userId = userId;
    }
}
