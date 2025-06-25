package de.hsrt.db2.TeleConsultCore.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Patient Entity – Derived from User
 *
 * @author Frederik Beimgraben
 * @see User
 */
public class Patient extends User {
    @Getter
    @Setter
    private String insurance;
}
