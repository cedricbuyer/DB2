package de.hsrt.db2.TeleConsultCore.service;

import de.hsrt.db2.TeleConsultCore.model.*;
import de.hsrt.db2.TeleConsultCore.command.RepoContext;
import de.hsrt.db2.TeleConsultCore.command.chat.ChatCommand;
import de.hsrt.db2.TeleConsultCore.command.message.MessageCommand;
import de.hsrt.db2.TeleConsultCore.command.user.UserCommand;
import de.hsrt.db2.TeleConsultCore.enums.ChatState;
import de.hsrt.db2.TeleConsultCore.enums.MessageState;
import de.hsrt.db2.TeleConsultCore.enums.UserType;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TeleConsultService {
    /**
     * Repos are automatically injected via the generated
     * constructor from @RequiredArgsConstructor.
     * See the
     * <a href="https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html">
     *      Spring Documentation regarding injection
     * </a> for more.
     */
    private final RepoContext dbContext;

    public User processUserCommand(UserCommand command) {
        return command.execute(dbContext);
    }
    public Message processMessageCommand(MessageCommand command) {
        return command.execute(dbContext);
    }
    public Chat processChatCommand(ChatCommand command) {
        return command.execute(dbContext);
    }

    public List<Chat> getChatsForUser(User user, @Nullable ChatState chatState) {
        return switch (user.getUserType()) {
            case GP -> dbContext
                    .getChatRepo()
                    .findByChatStateAndGp(chatState, (GP) user);
            case PATIENT -> dbContext
                    .getChatRepo()
                    .findByChatStateAndPatient(chatState, (Patient) user);
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

    public List<User> getAllUsers(UserType userType) {
        return switch (userType) {
            case GP -> new ArrayList<>(dbContext.getGpUserRepo().findAll());
            case PATIENT -> new ArrayList<>(dbContext.getPatientUserRepo().findAll());
            case null -> dbContext.getUserRepo().findAll();
        };
    }

    public List<User> searchUsers(String firstName, String lastName, UserType userType) {
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
