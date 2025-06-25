package de.hsrt.db2.TeleConsultCore.command;

@FunctionalInterface
public interface TeleConsultBaseCommand<T> {
    T execute(RepoContext ctx);
}
