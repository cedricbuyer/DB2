package de.hsrt.db2.TeleConsult.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Chat> chats = new ArrayList<>();
}
