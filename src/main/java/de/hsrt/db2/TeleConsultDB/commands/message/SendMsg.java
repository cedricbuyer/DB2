package de.hsrt.db2.TeleConsultDB.commands.message;

import de.hsrt.db2.TeleConsultDB.commands.DataBaseCommand;
import de.hsrt.db2.TeleConsultDB.commands.DataBaseContext;
import de.hsrt.db2.TeleConsultDB.enums.MessageState;
import de.hsrt.db2.TeleConsultDB.model.Chat;
import de.hsrt.db2.TeleConsultDB.model.Message;
import de.hsrt.db2.TeleConsultDB.model.User;
import jakarta.annotation.Nullable;
import lombok.Getter;

import java.sql.Blob;
import java.sql.Date;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record SendMsg (
        @Getter UUID chatID,
        @Getter UUID senderID,
        @Getter @Nullable String text,
        @Getter @Nullable Blob attachment
) implements MessageCommand {
    public SendMsg {
        // Either msg or attachment may be null
        if ((text == null || text.isEmpty()) && attachment == null) {
            throw new IllegalArgumentException("either msg or attachment must be set");
        }
    }

    @Override
    public Message execute(DataBaseContext ctx) {
        Optional<Chat> chat = ctx.getChatRepo().findById(chatID);
        if (chat.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + chatID + " not found!");
        }

        Optional<User> sender = ctx.getUserRepo().findById(senderID);
        if (sender.isEmpty()) {
            throw new NoSuchElementException("Sender with ID " + senderID + " not found!");
        }

        UUID gpID = chat.get().getGp().getId();
        UUID patientID = chat.get().getPatient().getId();

        // Assert, the User is part of the Chat
        if (!gpID.equals(senderID) && !patientID.equals(senderID)) {
            throw new IllegalArgumentException(
                    "Sender is not in chat with ID " + chatID + " – gp: " + gpID + " ; patient: " + patientID + " ; sender: " + senderID
            );
        }

        // Create Message Object
        Message msg = new Message();
        msg.setText(text);
        msg.setSender(sender.get());
        msg.setChat(chat.get());
        msg.setAttachment(attachment);
        msg.setDate(new Date(Instant.now().toEpochMilli()));
        msg.setState(MessageState.UNREAD);

        return ctx.getMessageRepo().save(msg);
    }
}
