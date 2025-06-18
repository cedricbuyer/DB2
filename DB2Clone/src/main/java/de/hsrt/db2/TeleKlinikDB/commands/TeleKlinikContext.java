package de.hsrt.db2.TeleKlinikDB.commands;

import de.hsrt.db2.TeleKlinikDB.model.GP;
import de.hsrt.db2.TeleKlinikDB.model.Patient;
import de.hsrt.db2.TeleKlinikDB.model.User;
import de.hsrt.db2.TeleKlinikDB.repo.ChatRepo;
import de.hsrt.db2.TeleKlinikDB.repo.MessageRepo;
import de.hsrt.db2.TeleKlinikDB.repo.UserRepo;
import lombok.Getter;

public record TeleKlinikContext(
        @Getter UserRepo<User>    userRepo,
        @Getter UserRepo<GP>      gpUserRepo,
        @Getter UserRepo<Patient> patientUserRepo,
        @Getter MessageRepo       messageRepo,
        @Getter ChatRepo          chatRepo
) { }
