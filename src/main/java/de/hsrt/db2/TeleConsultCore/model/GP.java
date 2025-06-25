package de.hsrt.db2.TeleConsultCore.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Practitioner Entity – Derived from User
 *
 * @author Frederik Beimgraben
 * @see User
 */
public class GP extends User {
    @Getter
    @Setter
    private String profession;
}
