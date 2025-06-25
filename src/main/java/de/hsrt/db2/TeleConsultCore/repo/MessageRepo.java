package de.hsrt.db2.TeleConsultCore.repo;

import de.hsrt.db2.TeleConsultCore.enums.ChatState;
import de.hsrt.db2.TeleConsultCore.enums.MessageState;
import de.hsrt.db2.TeleConsultCore.model.Chat;
import de.hsrt.db2.TeleConsultCore.model.Message;
import de.hsrt.db2.TeleConsultCore.model.User;

import java.util.List;

public interface MessageRepo extends BaseRepository<Message> {
    List<Message> findByChatAndStateAndSender(Chat chat, MessageState msgState, User sender);
    List<Message> findByChat(Chat chat);
}
