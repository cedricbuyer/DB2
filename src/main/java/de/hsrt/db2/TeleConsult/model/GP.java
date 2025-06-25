package de.hsrt.db2.TeleConsult.model;

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
    private String profession;
}
