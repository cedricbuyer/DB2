package de.hsrt.mis.TeleKlinikAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/* We use Users instead of User as spring security has an embedded class called User, so
 * sometimes can be mistakes when importing this class
 */
@Entity
public class UserEntity {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String gender; // FIXME: Enum Type

    @Getter
    @Setter
    private Date birthdate;

    @Override
    public String toString() {
        return String.format(
                "UserEntity{id=%d, name='%s', lastname='%s', password='%s', gender='%s', birthdate='%s'}",
                id,
                name,
                password,
                gender,
                birthdate // FIXME: String conversion
        );
    }
}