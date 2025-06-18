package de.hsrt.db2.TeleKlinikDB.commands.message;

import lombok.Getter;

import java.sql.Blob;

public class SendMsg extends MsgCommand {
    // Chat the Message is sent to
    @Getter
    private final int chatId;

    // Message Text
    @Getter
    private final String msg;

    // Attachment File Blob
    @Getter
    private final Blob attachment;

    public SendMsg(int chatId, String msg, Blob attachment) {
        this.chatId = chatId;

        // Either msg or attachment may be null
        if ((msg == null || msg.isEmpty()) && attachment == null) {
            throw new IllegalArgumentException("either msg or attachment must be set");
        }

        this.msg = msg;
        this.attachment = attachment;
    }
}
