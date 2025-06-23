package de.hsrt.db2.TeleConsultDB.commands.user;

import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommand;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommandResult;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultContext;
import de.hsrt.db2.TeleConsultDB.enums.UserType;
import de.hsrt.db2.TeleConsultDB.model.GP;
import de.hsrt.db2.TeleConsultDB.model.Patient;
import de.hsrt.db2.TeleConsultDB.model.User;
import jakarta.annotation.Nullable;
import lombok.Getter;

import java.sql.Date;
import java.util.Optional;

public record CreateUser (
        @Getter String name,
        @Getter String lastname,
        @Getter String gender,
        @Getter Date birthdate,
        @Getter UserType userType,
        @Getter @Nullable String profession,
        @Getter @Nullable String insurance
) implements TeleConsultCommand {
    @Override
    public TeleConsultCommandResult execute(TeleConsultContext ctx) {
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

                yield new TeleConsultCommandResult(
                        Optional.of(ctx.getGpUserRepo().save((GP) user).getId())
                );
            }
            case PATIENT -> {
                if (insurance == null || insurance.isEmpty()) {
                    throw new IllegalArgumentException("Insurance must be set for UserType.PATIENT");
                }

                ((Patient) user).setInsurance(insurance);

                yield new TeleConsultCommandResult(
                        Optional.of(ctx.getPatientUserRepo().save((Patient) user).getId())
                );
            }
        };
    }
}
