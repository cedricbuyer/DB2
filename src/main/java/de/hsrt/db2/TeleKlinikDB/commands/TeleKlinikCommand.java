package de.hsrt.db2.TeleKlinikDB.commands;

@FunctionalInterface
public interface TeleKlinikCommand {
    TeleKlinikCommandResult execute(TeleKlinikContext ctx);
}
