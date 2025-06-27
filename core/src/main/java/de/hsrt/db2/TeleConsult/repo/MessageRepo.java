package de.hsrt.db2.TeleConsult.repo;

import de.hsrt.db2.TeleConsult.enums.MessageState;
import de.hsrt.db2.TeleConsult.model.Chat;
import de.hsrt.db2.TeleConsult.model.Message;
import de.hsrt.db2.TeleConsult.model.User;

import java.util.List;

public interface MessageRepo extends BaseRepository<Message> {
    List<Message> findByChatAndStateAndSender(Chat chat, MessageState msgState, User sender);
    List<Message> findByChat(Chat chat);
}
