package de.hsrt.db2.TeleKlinikDB.commands.message;

import lombok.Getter;

import java.util.UUID;

public class EditMsg extends MsgCommand {
    @Getter
    private final UUID messageID;

    @Getter
    private final String text;

    public EditMsg(UUID messageID, String text) {
        this.messageID = messageID;

        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty!");
        }

        this.text = text;
    }
}
