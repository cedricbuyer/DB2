package de.hsrt.db2.TeleKlinikDB.enums;

import jakarta.persistence.Embeddable;

@Embeddable
public enum ChatState {
    ACTIVE,
    ARCHIVED
}
