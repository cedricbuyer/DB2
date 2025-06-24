package de.hsrt.db2.TeleConsultDB.service;

import de.hsrt.db2.TeleConsultDB.commands.DataBaseCommand;
import de.hsrt.db2.TeleConsultDB.commands.DataBaseContext;
import de.hsrt.db2.TeleConsultDB.enums.ChatState;
import de.hsrt.db2.TeleConsultDB.enums.MessageState;
import de.hsrt.db2.TeleConsultDB.model.Chat;
import de.hsrt.db2.TeleConsultDB.model.Message;
import de.hsrt.db2.TeleConsultDB.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeleConsultDBService {
    /**
     * Repos are automatically injected via the generated
     * constructor from @RequiredArgsConstructor.
     * See the
     * <a href="https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html">
     *      Spring Documentation regarding injection
     * </a> for more.
     */
    private final DataBaseContext dbContext;

    public <T> T processCommand(DataBaseCommand<T> command) {
        return command.execute(dbContext);
    }

    public List<Message> getUnreadMessages(User user) {
        List<Chat> userChats = dbContext.getChatRepo().findByChatStateAndPatientIdOrGpId(
                ChatState.ACTIVE,
                user.getId(),
                user.getId()
        );

        List<Message> messages = new ArrayList<>();

        for (Chat chat : userChats) {
            List<Message> chatMessages = dbContext.getMessageRepo().findByChatAndState(
                    chat,
                    MessageState.UNREAD
            );

            messages.addAll(chatMessages);
        }

        return messages;
    }
}
