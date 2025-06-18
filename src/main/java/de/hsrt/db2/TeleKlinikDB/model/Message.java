package de.hsrt.db2.TeleKlinikDB.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

import static jakarta.persistence.CascadeType.REMOVE;

@Entity
public class Message {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UUID;

    @Getter
    @ManyToOne(optional = false, cascade = REMOVE)
    private User sender;

    @Getter
    @ManyToOne(optional = false, cascade = REMOVE)
    private Chat chat;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    @Lob
    private byte[] attachment;
}
