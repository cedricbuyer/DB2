package de.hsrt.db2.TeleConsultDB.commands;

import de.hsrt.db2.TeleConsultDB.model.GP;
import de.hsrt.db2.TeleConsultDB.model.Patient;
import de.hsrt.db2.TeleConsultDB.model.User;
import de.hsrt.db2.TeleConsultDB.repo.ChatRepo;
import de.hsrt.db2.TeleConsultDB.repo.MessageRepo;
import de.hsrt.db2.TeleConsultDB.repo.UserRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public record TeleConsultContext (
        @Getter UserRepo<User>    userRepo,
        @Getter UserRepo<GP>      gpUserRepo,
        @Getter UserRepo<Patient> patientUserRepo,
        @Getter MessageRepo       messageRepo,
        @Getter ChatRepo          chatRepo
) { }
