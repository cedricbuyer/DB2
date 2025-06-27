package de.hsrt.db2.TeleConsult.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Patient Entity – Derived from User
 *
 * @author Frederik Beimgraben
 * @see User
 */
@Entity
public class Patient extends User {
    @Getter
    @Setter
    private String insurance;
}
