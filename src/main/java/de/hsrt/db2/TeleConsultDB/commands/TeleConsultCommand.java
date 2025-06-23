package de.hsrt.db2.TeleConsultDB.commands;

@FunctionalInterface
public interface TeleConsultCommand {
    TeleConsultCommandResult execute(TeleConsultContext ctx);
}
