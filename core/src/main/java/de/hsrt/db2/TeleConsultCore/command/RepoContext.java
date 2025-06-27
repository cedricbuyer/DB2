package de.hsrt.db2.TeleConsultCore.command;

import lombok.Getter;

public record RepoContext(
        @Getter UserRepo<User> userRepo,
        @Getter UserRepo<GP> gpUserRepo,
        @Getter UserRepo<Patient> patientUserRepo,
        @Getter MessageRepo messageRepo,
        @Getter ChatRepo chatRepo
) { }
