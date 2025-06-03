package de.hsrt.mis.IdentityManagementAL.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/* We use Users instead of User as spring security has an embeeded class called User, so
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
    private String username;

    @Getter
    @Setter
    private String password;

    @Override
    public String toString() {
        return String.format(
                "UserEntity{id=%d, username='%s', password='%s'}",
                id,
                username,
                password
        );
    }
}