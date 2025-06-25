package de.hsrt.db2.TeleConsultDB.service;

import de.hsrt.db2.TeleConsultDB.commands.DataBaseCommand;
import de.hsrt.db2.TeleConsultDB.enums.UserType;
import de.hsrt.db2.TeleConsultDB.model.*;
import de.hsrt.db2.TeleConsultDB.repo.DataBaseContext;
import de.hsrt.db2.TeleConsultDB.enums.ChatState;
import de.hsrt.db2.TeleConsultDB.enums.MessageState;
import de.hsrt.db2.TeleConsultDB.repo.MessageRepo;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
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
    private final MessageRepo messageRepo;

    public <T> T processCommand(DataBaseCommand<T> command) {
        return command.execute(dbContext);
    }

    public List<Chat> getChatsForUser(User user, @Nullable ChatState chatState) {
        return switch (user.getUserType()) {
            case GP -> dbContext
                    .getChatRepo()
                    .findByChatStateAndGpId(chatState, user.getId());
            case PATIENT -> dbContext
                    .getChatRepo()
                    .findByChatStateAndPatientId(chatState, user.getId());
        };
    }

    public List<Message> getUnreadMessages(User user) {
        List<Chat> userChats = getChatsForUser(user, ChatState.ACTIVE);

        List<Message> messages = new ArrayList<>();

        for (Chat chat : userChats) {
            User chatPartner = user.getId().equals(chat.getGp().getId()) ? chat.getPatient() : chat.getGp();

            List<Message> chatMessages = dbContext.getMessageRepo().findByChatAndStateAndSender(
                    chat,
                    MessageState.UNREAD,
                    chatPartner
            );

            messages.addAll(chatMessages);
        }

        return messages;
    }

    public List<Message> getMessagesForChat(Chat chat) {
        return dbContext.getMessageRepo().findByChat(chat);
    }

    public List<User> getAllUsers(UserType userType, DataBaseContext dataBaseContext) {
        return switch (userType) {
            case GP -> new ArrayList<>(dataBaseContext.getGpUserRepo().findAll());
            case PATIENT -> new ArrayList<>(dataBaseContext.getPatientUserRepo().findAll());
            case null -> dataBaseContext.getUserRepo().findAll();
        };
    }

    public List<User> searchUsers(String firstName, String lastName, UserType userType, DataBaseContext dataBaseContext) {
        return switch (userType) {
            case GP -> new ArrayList<>(
                    dbContext
                        .gpUserRepo()
                        .findByNameContainsOrLastnameContains(firstName, lastName)
            );
            case PATIENT -> new ArrayList<>(
                    dbContext
                        .patientUserRepo()
                        .findByNameContainsOrLastnameContains(firstName, lastName)
            );
            case null -> dbContext
                    .userRepo()
                    .findByNameContainsOrLastnameContains(firstName, lastName);
        };
    }
}
