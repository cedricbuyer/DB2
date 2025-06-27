package de.hsrt.db2.TeleConsult.repo;

import de.hsrt.db2.TeleConsult.model.GP;
import de.hsrt.db2.TeleConsult.model.Patient;
import de.hsrt.db2.TeleConsult.model.User;

public interface RepoContext {
    UserRepo<User> getUserRepo();
    UserRepo<GP> getGpUserRepo();
    UserRepo<Patient> getPatientUserRepo();
    MessageRepo getMessageRepo();
    ChatRepo getChatRepo();
}
