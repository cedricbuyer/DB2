package de.hsrt.db2.TeleKlinikDB.commands.message;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommand;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommandResult;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikContext;
import de.hsrt.db2.TeleKlinikDB.model.Chat;
import de.hsrt.db2.TeleKlinikDB.model.Message;
import de.hsrt.db2.TeleKlinikDB.model.User;
import lombok.Getter;

import java.sql.Blob;
import java.sql.Date;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public record SendMsg (
        @Getter UUID chatID,
        @Getter String text,
        @Getter Blob attachment,
        @Getter UUID senderID
) implements TeleKlinikCommand {
    public SendMsg {
        // Either msg or attachment may be null
        if ((text == null || text.isEmpty()) && attachment == null) {
            throw new IllegalArgumentException("either msg or attachment must be set");
        }
    }

    @Override
    public TeleKlinikCommandResult execute(TeleKlinikContext ctx) {
        Optional<Chat> chat = ctx.getChatRepo().findById(chatID);
        if (chat.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + chatID + " not found!");
        }

        Optional<User> sender = ctx.getUserRepo().findById(senderID);
        if (sender.isEmpty()) {
            throw new NoSuchElementException("Sender with ID " + senderID + " not found!");
        }

        // Assert, the User is part of the Chat
        if (!(chat.get().getGp().equals(sender.get()) || chat.get().getPatient().equals(sender.get()))) {
            throw new IllegalArgumentException("Sender is not in chat with ID " + chatID);
        }

        // Create Message Object
        Message msg = new Message();
        msg.setText(text);
        msg.setSender(sender.get());
        msg.setChat(chat.get());
        msg.setAttachment(attachment);
        msg.setDate((Date) Date.from(Instant.now()));

        return new TeleKlinikCommandResult(Optional.of(ctx.getMessageRepo().save(msg).getId()));
    }
}
