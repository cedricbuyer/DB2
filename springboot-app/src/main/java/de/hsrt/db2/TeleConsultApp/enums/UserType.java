package de.hsrt.db2.TeleConsultDB.enums;

/**
 * Enum to differentiate between GPs and Users in `CreateUser`-Command
 *
 * @author Frederik Beimgraben
 * @see de.hsrt.db2.TeleConsultDB.commands.user.CreateUser
 */
public enum UserType {
    GP,
    PATIENT
}
