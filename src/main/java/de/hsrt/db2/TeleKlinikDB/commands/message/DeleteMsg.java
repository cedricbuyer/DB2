package de.hsrt.db2.TeleKlinikDB.commands.message;

import lombok.Getter;

import java.util.UUID;

public class DeleteMsg extends MsgCommand {
    @Getter
    private final UUID messageID;

    public DeleteMsg(UUID messageID) {
        this.messageID = messageID;
    }
}
