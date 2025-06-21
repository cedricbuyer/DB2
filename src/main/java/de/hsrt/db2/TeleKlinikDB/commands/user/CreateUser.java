package de.hsrt.db2.TeleKlinikDB.commands.user;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommand;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommandResult;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.enums.UserType;
import de.hsrt.db2.TeleKlinikDB.model.GP;
import de.hsrt.db2.TeleKlinikDB.model.Patient;
import de.hsrt.db2.TeleKlinikDB.model.User;
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
) implements TeleKlinikCommand {
    @Override
    public TeleKlinikCommandResult execute(TeleKlinikContext ctx) {
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

                yield new TeleKlinikCommandResult(
                        Optional.of(ctx.getGpUserRepo().save((GP) user).getId())
                );
            }
            case PATIENT -> {
                if (insurance == null || insurance.isEmpty()) {
                    throw new IllegalArgumentException("Insurance must be set for UserType.PATIENT");
                }

                ((Patient) user).setInsurance(insurance);

                yield new TeleKlinikCommandResult(
                        Optional.of(ctx.getPatientUserRepo().save((Patient) user).getId())
                );
            }
        };
    }
}
