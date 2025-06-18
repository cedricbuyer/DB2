package de.hsrt.db2.TeleKlinikDB.commands.user;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommandResult;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.enums.Gender;
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
        @Getter Gender gender,
        @Getter Date birthdate,
        @Getter UserType userType,
        @Getter @Nullable String profession,
        @Getter @Nullable String insurance
) implements UserCommand {
    @Override
    public TeleKlinikCommandResult execute(TeleKlinikContext ctx) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty!");
        }

        if (lastname == null || lastname.isEmpty()) {
            throw new IllegalArgumentException("Lastname cannot be null or empty!");
        }

        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be empty!");
        }

        if (birthdate == null) {
            throw new IllegalArgumentException("Birthdate cannot be null!");
        }

        if (birthdate.after(new Date(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("Birthdate cannot be in the future!");
        }

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
