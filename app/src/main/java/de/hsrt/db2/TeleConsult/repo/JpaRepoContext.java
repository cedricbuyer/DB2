package de.hsrt.db2.TeleConsult.repo;

import de.hsrt.db2.TeleConsult.model.GP;
import de.hsrt.db2.TeleConsult.model.Patient;
import de.hsrt.db2.TeleConsult.model.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public record JpaRepoContext(
        @Getter UserJpaRepo<User> userRepo,
        @Getter UserJpaRepo<GP> gpUserRepo,
        @Getter UserJpaRepo<Patient> patientUserRepo,
        @Getter MessageJpaRepo messageRepo,
        @Getter ChatJpaRepo chatRepo
) implements RepoContext { }
