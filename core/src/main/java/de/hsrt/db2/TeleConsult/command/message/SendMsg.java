package de.hsrt.db2.TeleConsult.command.message;

import de.hsrt.db2.TeleConsult.model.Chat;
import de.hsrt.db2.TeleConsult.model.Message;
import de.hsrt.db2.TeleConsult.model.User;
import de.hsrt.db2.TeleConsult.repo.RepoContext;
import de.hsrt.db2.TeleConsult.enums.MessageState;
import lombok.Getter;

import java.sql.Blob;
import java.sql.Date;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record SendMsg (
        @Getter UUID chatID,
        @Getter UUID senderID,
        @Getter String text,
        @Getter Blob attachment
) implements MessageCommand {
    public SendMsg {
        // Either msg or attachment may be null
        if ((text == null || text.isEmpty()) && attachment == null) {
            throw new IllegalArgumentException("either msg or attachment must be set");
        }

        if (text != null && checkContainsIllegalChar(text)) {
            throw new IllegalArgumentException("text contains illegal characters");
        }
    }

    @Override
    public Message execute(RepoContext ctx) {
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
                    "Sender is not in chat with ID " + chatID
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

    public boolean checkContainsIllegalChar(String text) {
        String illegalCharsPattern = "[^a-zA-Z0-9\\s.,!?;:()\\-]";
        Pattern pattern = Pattern.compile(illegalCharsPattern);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
