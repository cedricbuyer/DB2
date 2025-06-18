package de.hsrt.db2.TeleKlinikDB.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Patient extends UserEntity {
    @Getter
    @Setter
    private String insurance;
}
