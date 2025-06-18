package de.hsrt.db2.TeleKlinikDB.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class  GP extends UserEntity {
    @Getter
    @Setter
    private String profession;
}
