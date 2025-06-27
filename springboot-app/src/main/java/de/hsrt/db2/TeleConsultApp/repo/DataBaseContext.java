package de.hsrt.db2.TeleConsultDB.repo;

import de.hsrt.db2.TeleConsultDB.model.GP;
import de.hsrt.db2.TeleConsultDB.model.Patient;
import de.hsrt.db2.TeleConsultDB.model.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public record DataBaseContext(
        @Getter UserRepo<User>    userRepo,
        @Getter UserRepo<GP>      gpUserRepo,
        @Getter UserRepo<Patient> patientUserRepo,
        @Getter MessageRepo       messageRepo,
        @Getter ChatRepo          chatRepo
) { }
