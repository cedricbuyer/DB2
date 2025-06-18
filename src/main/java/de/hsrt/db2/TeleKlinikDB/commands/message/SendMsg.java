package de.hsrt.db2.TeleKlinikDB.commands.message;

import de.hsrt.db2.TeleKlinikDB.model.Chat;
import de.hsrt.db2.TeleKlinikDB.model.Message;
import de.hsrt.db2.TeleKlinikDB.model.User;
import de.hsrt.db2.TeleKlinikDB.repo.ChatRepo;
import de.hsrt.db2.TeleKlinikDB.repo.MessageRepo;
import de.hsrt.db2.TeleKlinikDB.repo.UserRepo;
import lombok.Getter;

import java.sql.Blob;
import java.sql.Date;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class SendMsg implements MsgCommand {
    // Chat the Message is sent to
    @Getter
    private final UUID chatID;

    // Message Text
    @Getter
    private final String text;

    // Attachment File Blob
    @Getter
    private final Blob attachment;

    // User
    @Getter
    private final UUID sender;

    public SendMsg(UUID chatID, UUID sender, String text, Blob attachment) {
        this.chatID = chatID;

        // Either msg or attachment may be null
        if ((text == null || text.isEmpty()) && attachment == null) {
            throw new IllegalArgumentException("either msg or attachment must be set");
        }

        this.text = text;
        this.attachment = attachment;
        this.sender = sender;
    }

    public void execute(ChatRepo chatRepo, UserRepo<User> userRepo, MessageRepo messageRepo) {
        Optional<Chat> chat = chatRepo.findById(this.chatID);
        if (chat.isEmpty()) {
            throw new NoSuchElementException("Chat with ID " + this.chatID + " not found!");
        }

        Optional<User> sender = userRepo.findById(this.sender);
        if (sender.isEmpty()) {
            throw new NoSuchElementException("Sender with ID " + this.sender + " not found!");
        }

        // Assert, the User is part of the Chat
        if (!(chat.get().getGp().equals(sender.get()) || chat.get().getPatient().equals(sender.get()))) {
            throw new IllegalArgumentException("Sender is not in chat with ID " + this.chatID);
        }

        // Create Message Object
        Message msg = new Message();
        msg.setText(this.text);
        msg.setSender(sender.get());
        msg.setChat(chat.get());
        msg.setAttachment(this.attachment);
        msg.setDate((Date) Date.from(Instant.now()));
        messageRepo.save(msg);
    }
}
