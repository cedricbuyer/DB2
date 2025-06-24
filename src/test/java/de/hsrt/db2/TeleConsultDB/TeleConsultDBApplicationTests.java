package de.hsrt.db2.TeleConsultDB;

import de.hsrt.db2.TeleConsultDB.commands.chat.ChatCommand;
import de.hsrt.db2.TeleConsultDB.commands.chat.CreateChat;
import de.hsrt.db2.TeleConsultDB.commands.user.CreateUser;
import de.hsrt.db2.TeleConsultDB.commands.user.UserCommand;
import de.hsrt.db2.TeleConsultDB.enums.UserType;
import de.hsrt.db2.TeleConsultDB.model.Chat;
import de.hsrt.db2.TeleConsultDB.model.User;
import de.hsrt.db2.TeleConsultDB.service.TeleConsultDBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

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
	@test
	void sendMsgTest (){
      	//Nutzer und Chat erstellen
		User gp = consultService.processCommand(new CreateUser(
				"Max", "Mustermann", "M",
				Date.valueOf(LocalDate.of(1998, 2, 12)),
				UserType.GP, "General Practitioner", null));

		User patient = consultService.processCommand(new CreateUser(
				"Anja", "Musterfrau", "F",
				Date.valueOf(LocalDate.of(1995, 1, 1)),
				UserType.PATIENT, null, null));

		string messageTest = "Hallo!";

		Chat chat = consultService.processCommand(new CreateChat(gp.getUserID(), patient.getUserID()));

		//Nachricht senden
		Message msg = consultService.processCommand(new SendMsg(chat.getChatID(), gp.getUserID(), messageTest, null));

		//Prüfung

		if (msg == null)
			throw new AssertionError("Sent message is null");

		if (msg != null)
			System.out.println("" + messageTest);

		if (gp.getUserID() == msg.getSender().getUserID())
			System.out.println("Correct Sender");


	}
}