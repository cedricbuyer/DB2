package de.hsrt.db2.TeleConsultDB;

import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommand;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommandResult;
import de.hsrt.db2.TeleConsultDB.commands.chat.CreateChat;
import de.hsrt.db2.TeleConsultDB.commands.user.CreateUser;
import de.hsrt.db2.TeleConsultDB.enums.UserType;
import de.hsrt.db2.TeleConsultDB.service.TeleConsultDBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class TeleConsultDBApplicationTests {
	@Autowired
	TeleConsultDBService consultService;

	@Test
	void createUserTest() {
		TeleConsultCommand cmd = new CreateUser(
				"Max",
				"Mustermann",
				"M",
				Date.valueOf(LocalDate.of(2014, 2, 11)),
				UserType.GP,
				"Oncologist",
				null
		);

		TeleConsultCommandResult result = consultService.processTeleConsultCommand(cmd);

		System.out.println(result);
	}

	@Test
	void createChatTest() {
		TeleConsultCommand cmdCreateGP = new CreateUser(
				"Anja",
				"Musterfrau",
				"F",
				Date.valueOf(LocalDate.of(1990, 4, 13)),
				UserType.GP,
				"Oncologist",
				null
		);

		TeleConsultCommandResult resultCreateGP = consultService.processTeleConsultCommand(cmdCreateGP);

		if (resultCreateGP.createdObjectID().isEmpty())
			throw new AssertionError("Created GP ID is empty");

		UUID gpID = resultCreateGP.createdObjectID().get();

		TeleConsultCommand cmdCreatePatient = new CreateUser(
				"Max",
				"Mustermann",
				"M",
				Date.valueOf(LocalDate.of(1985, 7, 3)),
				UserType.PATIENT,
				null,
				"AOK"
		);

		TeleConsultCommandResult resultCreatePatient = consultService.processTeleConsultCommand(cmdCreatePatient);

		if (resultCreatePatient.createdObjectID().isEmpty())
			throw new AssertionError("Created patient ID is empty");

		UUID patID = resultCreatePatient.createdObjectID().get();

		TeleConsultCommand cmd = new CreateChat(gpID, patID);

		TeleConsultCommandResult result = consultService.processTeleConsultCommand(cmd);

		if (result.createdObjectID().isEmpty())
			throw new AssertionError("Created chat ID is empty");

		System.out.println("Created chat: " + result + " of GP: " + gpID + " and pat: " + patID);
	}
}
