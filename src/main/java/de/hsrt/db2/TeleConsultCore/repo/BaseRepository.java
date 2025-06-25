package de.hsrt.db2.TeleConsultCore.repo;

import java.util.Optional;
import java.util.UUID;

public interface BaseRepository<T> {
    <S extends T> S save(S element);
    void delete(T element);
    Optional<T> findById(UUID id);
}
