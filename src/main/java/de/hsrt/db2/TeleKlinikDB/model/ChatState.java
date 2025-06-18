package de.hsrt.db2.TeleKlinikDB.model;

import jakarta.persistence.Embeddable;

@Embeddable
public enum ChatState {
    ACTIVE,
    ARCHIVED
}
