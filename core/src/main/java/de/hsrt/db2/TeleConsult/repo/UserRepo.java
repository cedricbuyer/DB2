package de.hsrt.db2.TeleConsult.repo;

import de.hsrt.db2.TeleConsult.model.Chat;
import de.hsrt.db2.TeleConsult.model.User;

import java.util.List;

public interface UserRepo<T extends User> extends BaseRepository<T> {
    List<T> findAll();
    List<T> findByNameContainsOrLastnameContains(String name, String lastname);
}
