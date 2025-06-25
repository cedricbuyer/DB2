package de.hsrt.db2.TeleConsultCore.command;

import de.hsrt.db2.TeleConsultCore.model.GP;
import de.hsrt.db2.TeleConsultCore.model.Patient;
import de.hsrt.db2.TeleConsultCore.model.User;
import de.hsrt.db2.TeleConsultCore.repo.ChatRepo;
import de.hsrt.db2.TeleConsultCore.repo.MessageRepo;
import de.hsrt.db2.TeleConsultCore.repo.UserRepo;
import lombok.Getter;

public record RepoContext(
        @Getter UserRepo<User> userRepo,
        @Getter UserRepo<GP> gpUserRepo,
        @Getter UserRepo<Patient> patientUserRepo,
        @Getter MessageRepo messageRepo,
        @Getter ChatRepo chatRepo
) { }
