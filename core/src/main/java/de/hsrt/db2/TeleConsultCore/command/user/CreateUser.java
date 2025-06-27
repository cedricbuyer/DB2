package de.hsrt.db2.TeleConsultCore.command.user;

import de.hsrt.db2.TeleConsultCore.command.RepoContext;
import de.hsrt.db2.TeleConsultCore.model.GP;
import de.hsrt.db2.TeleConsultCore.model.Patient;
import de.hsrt.db2.TeleConsultCore.model.User;
import de.hsrt.db2.TeleConsultCore.enums.UserType;
import jakarta.annotation.Nullable;
import lombok.Getter;

import java.sql.Date;

public record CreateUser (
        @Getter String name,
        @Getter String lastname,
        @Getter String gender,
        @Getter Date birthdate,
        @Getter UserType userType,
        @Getter @Nullable String profession,
        @Getter @Nullable String insurance
) implements UserCommand {
    @Override
    public User execute(RepoContext ctx) {
        User user = switch (userType) {
            case GP -> new GP();
            case PATIENT -> new Patient();
        };

        user.setName(name);
        user.setLastname(lastname);
        user.setGender(gender);
        user.setBirthdate(birthdate);

        return switch (userType) {
            case GP -> {
                if (profession == null || profession.isEmpty()) {
                    throw new IllegalArgumentException("Profession must be set for UserType.GP");
                }

                ((GP) user).setProfession(profession);

                yield ctx.gpUserRepo().save((GP) user);
            }
            case PATIENT -> {
                if (insurance == null || insurance.isEmpty()) {
                    throw new IllegalArgumentException("Insurance must be set for UserType.PATIENT");
                }

                ((Patient) user).setInsurance(insurance);

                yield ctx.patientUserRepo().save((Patient) user);
            }
        };
    }
}
