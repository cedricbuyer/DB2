package de.hsrt.db2.TeleKlinikDB.commands;

@FunctionalInterface
public interface TeleKlinikCommand {
    void execute(TeleKlinikContext ctx);
}
