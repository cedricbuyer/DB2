package de.hsrt.db2.TeleConsultDB.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Practitioner Entity – Derived from User
 *
 * @author Frederik Beimgraben
 * @see User
 */
@Entity
public class GP extends User {
    @Getter
    @Setter
    private String profession;
}
