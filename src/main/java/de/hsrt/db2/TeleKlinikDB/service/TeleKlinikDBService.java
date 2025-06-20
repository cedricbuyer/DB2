package de.hsrt.db2.TeleKlinikDB.service;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommand;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommandResult;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.model.GP;
import de.hsrt.db2.TeleKlinikDB.model.Patient;
import de.hsrt.db2.TeleKlinikDB.model.User;
import de.hsrt.db2.TeleKlinikDB.repo.ChatRepo;
import de.hsrt.db2.TeleKlinikDB.repo.MessageRepo;
import de.hsrt.db2.TeleKlinikDB.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeleKlinikDBService {
    @Autowired MessageRepo messageRepo;
    @Autowired ChatRepo chatRepo;
    @Autowired UserRepo<User> userRepo;
    @Autowired UserRepo<GP> gpUserRepo;
    @Autowired UserRepo<Patient> patientUserRepo;

    public TeleKlinikContext getTeleKlinikContext() {
        return new TeleKlinikContext(
                userRepo,
                gpUserRepo,
                patientUserRepo,
                messageRepo,
                chatRepo
        );
    }

    public TeleKlinikCommandResult processTeleKlinikCommand(TeleKlinikCommand command) {
        return command.execute(getTeleKlinikContext());
    }
}
