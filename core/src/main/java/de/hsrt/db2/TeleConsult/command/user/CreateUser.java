package de.hsrt.db2.TeleConsult.command.user;

import de.hsrt.db2.TeleConsult.repo.RepoContext;
import de.hsrt.db2.TeleConsult.model.GP;
import de.hsrt.db2.TeleConsult.model.Patient;
import de.hsrt.db2.TeleConsult.model.User;
import de.hsrt.db2.TeleConsult.enums.UserType;
import lombok.Getter;

import java.sql.Date;

public record CreateUser (
        @Getter String name,
        @Getter String lastname,
        @Getter String gender,
        @Getter Date birthdate,
        @Getter UserType userType,
        @Getter String profession,
        @Getter String insurance
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

                yield ctx.getGpUserRepo().save((GP) user);
            }
            case PATIENT -> {
                if (insurance == null || insurance.isEmpty()) {
                    throw new IllegalArgumentException("Insurance must be set for UserType.PATIENT");
                }

                ((Patient) user).setInsurance(insurance);

                yield ctx.getPatientUserRepo().save((Patient) user);
            }
        };
    }
}
