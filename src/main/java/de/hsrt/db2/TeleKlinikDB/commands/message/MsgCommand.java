package de.hsrt.db2.TeleKlinikDB.commands.message;

import de.hsrt.db2.TeleKlinikDB.model.GP;
import de.hsrt.db2.TeleKlinikDB.model.Patient;
import de.hsrt.db2.TeleKlinikDB.model.User;
import de.hsrt.db2.TeleKlinikDB.repo.ChatRepo;
import de.hsrt.db2.TeleKlinikDB.repo.MessageRepo;
import de.hsrt.db2.TeleKlinikDB.repo.UserRepo;

@FunctionalInterface
public interface MsgCommand {
    void execute(ChatRepo chatRepo, UserRepo<User> userRepo, MessageRepo messageRepo);
}
