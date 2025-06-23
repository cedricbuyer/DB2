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
@RequiredArgsConstructor
public class TeleConsultContext {
    @Getter final UserRepo<User>    userRepo;
    @Getter final UserRepo<GP>      gpUserRepo;
    @Getter final UserRepo<Patient> patientUserRepo;
    @Getter final MessageRepo       messageRepo;
    @Getter final ChatRepo          chatRepo;
}
