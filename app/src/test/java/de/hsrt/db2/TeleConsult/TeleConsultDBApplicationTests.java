package de.hsrt.db2.TeleConsult;

import javax.sql.rowset.serial.SerialBlob;

import de.hsrt.db2.TeleConsult.command.chat.CreateChat;
import de.hsrt.db2.TeleConsult.command.message.MarkRead;
import de.hsrt.db2.TeleConsult.command.message.SendMsg;
import de.hsrt.db2.TeleConsult.command.user.CreateUser;
import de.hsrt.db2.TeleConsult.enums.UserType;
import de.hsrt.db2.TeleConsult.model.Chat;
import de.hsrt.db2.TeleConsult.model.Message;
import de.hsrt.db2.TeleConsult.model.User;
import de.hsrt.db2.TeleConsult.service.TeleConsultService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootTest
class TeleConsultDBApplicationTests {
	@Autowired
	TeleConsultService consultService;

	@Test
	void createUserTest() {
		User result = consultService.processUserCommand(new CreateUser(
				"Max",
				"Mustermann",
				"M",
				Date.valueOf(LocalDate.of(2014, 2, 11)),
				UserType.GP,
				"Oncologist",
				null
		));

		System.out.println(result);
	}

	@Test
	void createChatTest() {
		User gp = consultService.processUserCommand(new CreateUser(
				"Anja",
				"Musterfrau",
				"F",
				Date.valueOf(LocalDate.of(1990, 4, 13)),
				UserType.GP,
				"Oncologist",
				null
		));

		if (gp == null)
			throw new AssertionError("Created GP is null");

		User patient = consultService.processUserCommand(new CreateUser(
				"Max",
				"Mustermann",
				"M",
				Date.valueOf(LocalDate.of(1985, 7, 3)),
				UserType.PATIENT,
				null,
				"AOK"
		));

		if (patient == null)
			throw new AssertionError("Created patient is null");

		Chat result = consultService.processChatCommand(new CreateChat(gp.getId(), patient.getId()));

		if (result == null)
			throw new AssertionError("Created chat is null");

		System.out.println("Created chat: " + result + " of GP: " + gp.getId() + " and pat: " + patient.getId());
	}

	@Test
	void sendMsgTest (){
      	// Create Chat and Users
		User gp = consultService.processUserCommand(new CreateUser(
				"Max", "Mustermann", "M",
				Date.valueOf(LocalDate.of(1998, 2, 12)),
				UserType.GP, "General Practitioner", null));

		User patient = consultService.processUserCommand(new CreateUser(
				"Anja", "Musterfrau", "F",
				Date.valueOf(LocalDate.of(1995, 1, 1)),
				UserType.PATIENT, null, "AOK"));

		String messageTest = "Hallo!";

		Chat chat = consultService.processChatCommand(new CreateChat(gp.getId(), patient.getId()));

		// Send Message
		Message msg = consultService.processMessageCommand(new SendMsg(chat.getId(), gp.getId(), messageTest, null));

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
		User gp = consultService.processUserCommand(new CreateUser(
				"Max", "Mustermann", "M",
				Date.valueOf(LocalDate.of(1998, 2, 12)),
				UserType.GP, "General Practitioner", null));

		User patient = consultService.processUserCommand(new CreateUser(
				"Anja", "Musterfrau", "F",
				Date.valueOf(LocalDate.of(1995, 1, 1)),
				UserType.PATIENT, null, "AOK"));

		String messageTest = "Hallo!";

		Chat chat = consultService.processChatCommand(new CreateChat(gp.getId(), patient.getId()));

		// Send Message
		Message msg = consultService.processMessageCommand(new SendMsg(chat.getId(), gp.getId(), messageTest, null));

		System.out.println(msg.getId());

		// Get Unread Messages for Patient
		List<Message> unreadMessages = consultService.getUnreadMessages(patient);

		if (unreadMessages.stream().noneMatch(unread -> unread.getId().equals(msg.getId()))) {
			throw new AssertionError("Unread message not found");
		}

		// Mark Message read
		consultService.processMessageCommand(new MarkRead(msg.getId()));
		unreadMessages = consultService.getUnreadMessages(patient);

		if (!unreadMessages.isEmpty()) {
			throw new AssertionError("Message not marked as read");
		}
	}

	// Test to check if blob is working
	@Test
	void blobTest() {
		User gp = consultService.processUserCommand(new CreateUser(
				"Anja",
				"Anjason",
				"F",
				Date.valueOf(LocalDate.of(1990, 4, 13)),
				UserType.GP,
				"TestProfession",
				null
		));

		User patient = consultService.processUserCommand(new CreateUser(
				"Max",
				"Maxson",
				"M",
				Date.valueOf(LocalDate.of(1985, 7, 3)),
				UserType.PATIENT,
				null,
				"TestInsurance"
		));

		byte[] data = new byte[(int) 5e5];

		new Random().nextBytes(data);

		Blob newBlobAttachment;

		try {
			newBlobAttachment = new SerialBlob(data);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		Chat chat = consultService.processChatCommand(new CreateChat(gp.getId(), patient.getId()));

		Message msg = consultService.processMessageCommand(new SendMsg(chat.getId(), gp.getId(), null, newBlobAttachment));

		if (msg == null || msg.getAttachment() == null) {
			throw new AssertionError("Blob attachment is null or message is null");
		}

		if (msg.getText() != null) {
			throw new AssertionError("Text should be null");
		}
	}
}