package de.hsrt.db2.TeleConsult.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            mappedBy = "gp",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Chat> chats = new ArrayList<>();
}
