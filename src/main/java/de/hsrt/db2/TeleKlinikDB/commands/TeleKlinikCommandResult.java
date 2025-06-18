package de.hsrt.db2.TeleKlinikDB.commands;

import jakarta.annotation.Nullable;
import lombok.Getter;

import java.util.Optional;
import java.util.UUID;

public record TeleKlinikCommandResult (
        @Getter Optional<UUID> createdObjectID
) {
    public static TeleKlinikCommandResult emptyResult() {
        return new TeleKlinikCommandResult(Optional.empty());
    }
}
