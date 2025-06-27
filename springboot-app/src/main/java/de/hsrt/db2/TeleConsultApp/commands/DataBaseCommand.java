package de.hsrt.db2.TeleConsultDB.commands;

import de.hsrt.db2.TeleConsultDB.repo.DataBaseContext;

@FunctionalInterface
public interface DataBaseCommand<T> {
    T execute(DataBaseContext ctx);
}
