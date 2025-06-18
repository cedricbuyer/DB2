package de.hsrt.db2.TeleKlinikDB.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static jakarta.persistence.CascadeType.REMOVE;

@Entity
public class Chat {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = REMOVE)
    private GP gp;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = REMOVE)
    private Patient patient;

    @Getter
    @Setter
    @Embedded
    private ChatState chatState;
}
