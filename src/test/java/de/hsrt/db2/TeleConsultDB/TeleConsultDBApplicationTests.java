package de.hsrt.db2.TeleConsultDB;

import de.hsrt.db2.TeleConsultDB.commands.chat.ChatCommand;
import de.hsrt.db2.TeleConsultDB.commands.chat.CreateChat;
import de.hsrt.db2.TeleConsultDB.commands.message.MarkRead;
import de.hsrt.db2.TeleConsultDB.commands.message.SendMsg;
import de.hsrt.db2.TeleConsultDB.commands.user.CreateUser;
import de.hsrt.db2.TeleConsultDB.commands.user.UserCommand;
import de.hsrt.db2.TeleConsultDB.enums.UserType;
import de.hsrt.db2.TeleConsultDB.model.Chat;
import de.hsrt.db2.TeleConsultDB.model.Message;
import de.hsrt.db2.TeleConsultDB.model.User;
import de.hsrt.db2.TeleConsultDB.service.TeleConsultDBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class TeleConsultDBApplicationTests {
	@Autowired
	TeleConsultDBService consultService;

	@Test
	void createUserTest() {
		UserCommand cmd = new CreateUser(
				"Max",
				"Mustermann",
				"M",
				Date.valueOf(LocalDate.of(2014, 2, 11)),
				UserType.GP,
				"Oncologist",
				null
		);

		User result = consultService.processCommand(cmd);

		System.out.println(result);
	}

	@Test
	void createChatTest() {
		UserCommand cmdCreateGP = new CreateUser(
				"Anja",
				"Musterfrau",
				"F",
				Date.valueOf(LocalDate.of(1990, 4, 13)),
				UserType.GP,
				"Oncologist",
				null
		);

		User gp = consultService.processCommand(cmdCreateGP);

		if (gp == null)
			throw new AssertionError("Created GP is null");

		UserCommand cmdCreatePatient = new CreateUser(
				"Max",
				"Mustermann",
				"M",
				Date.valueOf(LocalDate.of(1985, 7, 3)),
				UserType.PATIENT,
				null,
				"AOK"
		);

		User patient = consultService.processCommand(cmdCreatePatient);

		if (patient == null)
			throw new AssertionError("Created patient is null");

		ChatCommand cmd = new CreateChat(gp.getId(), patient.getId());

		Chat result = consultService.processCommand(cmd);

		if (result == null)
			throw new AssertionError("Created chat is null");

		System.out.println("Created chat: " + result + " of GP: " + gp.getId() + " and pat: " + patient.getId());
	}

	@Test
	void sendMsgTest (){
      	// Create Chat and Users
		User gp = consultService.processCommand(new CreateUser(
				"Max", "Mustermann", "M",
				Date.valueOf(LocalDate.of(1998, 2, 12)),
				UserType.GP, "General Practitioner", null));

		User patient = consultService.processCommand(new CreateUser(
				"Anja", "Musterfrau", "F",
				Date.valueOf(LocalDate.of(1995, 1, 1)),
				UserType.PATIENT, null, "AOK"));

		String messageTest = "Hallo!";

		Chat chat = consultService.processCommand(new CreateChat(gp.getId(), patient.getId()));

		// Send Message
		Message msg = consultService.processCommand(new SendMsg(chat.getId(), gp.getId(), messageTest, null));

		// Check the Resulting State
		if (msg == null)
			throw new AssertionError("Sent message is null");

		System.out.println(messageTest);

		if (gp.getId() == msg.getSender().getId())
			System.out.println("Correct Sender");
	}

	@Test
	void getUnreadMessagesTest() {
		// Create Chat and Users
		User gp = consultService.processCommand(new CreateUser(
				"Max", "Mustermann", "M",
				Date.valueOf(LocalDate.of(1998, 2, 12)),
				UserType.GP, "General Practitioner", null));

		User patient = consultService.processCommand(new CreateUser(
				"Anja", "Musterfrau", "F",
				Date.valueOf(LocalDate.of(1995, 1, 1)),
				UserType.PATIENT, null, "AOK"));

		String messageTest = "Hallo!";

		Chat chat = consultService.processCommand(new CreateChat(gp.getId(), patient.getId()));

		// Send Message
		Message msg = consultService.processCommand(new SendMsg(chat.getId(), gp.getId(), messageTest, null));

		System.out.println(msg.getId());

		// Get Unread Messages for Patient
		List<Message> unreadMessages = consultService.getUnreadMessages(patient);

		if (unreadMessages.stream().noneMatch(unread -> unread.getId().equals(msg.getId()))) {
			throw new AssertionError("Unread message not found");
		}

		// Mark Message read
		consultService.processCommand(new MarkRead(msg.getId()));
		unreadMessages = consultService.getUnreadMessages(patient);

		if (!unreadMessages.isEmpty()) {
			throw new AssertionError("Message not marked as read");
		}
	}
}