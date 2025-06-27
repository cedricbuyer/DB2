package de.hsrt.db2.TeleConsult.command;

import de.hsrt.db2.TeleConsult.repo.RepoContext;

@FunctionalInterface
public interface TeleConsultBaseCommand<T> {
    T execute(RepoContext ctx);
}
