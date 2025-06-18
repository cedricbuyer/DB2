package de.hsrt.db2.TeleKlinikDB.commands.chat;

import de.hsrt.db2.TeleKlinikDB.model.GP;
import de.hsrt.db2.TeleKlinikDB.model.Patient;
import de.hsrt.db2.TeleKlinikDB.repo.ChatRepo;
import de.hsrt.db2.TeleKlinikDB.repo.UserRepo;

@FunctionalInterface
public interface ChatCommand {
    void execute(ChatRepo chatRepo, UserRepo<GP> gpRepo, UserRepo<Patient> patientRepo);
}
