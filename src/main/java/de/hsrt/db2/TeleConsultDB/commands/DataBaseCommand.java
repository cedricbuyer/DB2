package de.hsrt.db2.TeleConsultDB.commands;

@FunctionalInterface
public interface DataBaseCommand<T> {
    T execute(DataBaseContext ctx);
}
