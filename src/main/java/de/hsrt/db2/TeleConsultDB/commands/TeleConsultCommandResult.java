package de.hsrt.db2.TeleConsultDB.commands;

import lombok.Getter;

import java.util.Optional;
import java.util.UUID;

public record TeleConsultCommandResult(
        @Getter Optional<UUID> createdObjectID
) {
    public static TeleConsultCommandResult emptyResult() {
        return new TeleConsultCommandResult(Optional.empty());
    }
}
