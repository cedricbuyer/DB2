package de.hsrt.db2.TeleKlinikDB;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommand;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommandResult;
import de.hsrt.db2.TeleKlinikDB.commands.chat.CreateChat;
import de.hsrt.db2.TeleKlinikDB.commands.user.CreateUser;
import de.hsrt.db2.TeleKlinikDB.enums.UserType;
import de.hsrt.db2.TeleKlinikDB.service.TeleKlinikDBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class TeleKlinikDBApplicationTests {
	@Autowired
	TeleKlinikDBService consultService;

	@Test
	void createUserTest() {
		TeleKlinikCommand cmd = new CreateUser(
				"Max",
				"Mustermann",
				"M",
				Date.valueOf(LocalDate.of(2014, 2, 11)),
				UserType.GP,
				"Oncologist",
				null
		);

		TeleKlinikCommandResult result = consultService.processTeleKlinikCommand(cmd);

		System.out.println(result);
	}

	@Test
	void createChatTest() {
		TeleKlinikCommand cmdCreateGP = new CreateUser(
				"Anja",
				"Musterfrau",
				"F",
				Date.valueOf(LocalDate.of(1990, 4, 13)),
				UserType.GP,
				"Oncologist",
				null
		);

		TeleKlinikCommandResult resultCreateGP = consultService.processTeleKlinikCommand(cmdCreateGP);

		if (resultCreateGP.createdObjectID().isEmpty())
			throw new AssertionError("Created GP ID is empty");

		UUID gpID = resultCreateGP.createdObjectID().get();

		TeleKlinikCommand cmdCreatePatient = new CreateUser(
				"Max",
				"Mustermann",
				"M",
				Date.valueOf(LocalDate.of(1985, 7, 3)),
				UserType.PATIENT,
				null,
				"AOK"
		);

		TeleKlinikCommandResult resultCreatePatient = consultService.processTeleKlinikCommand(cmdCreatePatient);

		if (resultCreatePatient.createdObjectID().isEmpty())
			throw new AssertionError("Created patient ID is empty");

		UUID patID = resultCreatePatient.createdObjectID().get();

		TeleKlinikCommand cmd = new CreateChat(gpID, patID);

		TeleKlinikCommandResult result = consultService.processTeleKlinikCommand(cmd);

		if (result.createdObjectID().isEmpty())
			throw new AssertionError("Created chat ID is empty");

		System.out.println("Created chat: " + result + " of GP: " + gpID + " and pat: " + patID);
	}
}
