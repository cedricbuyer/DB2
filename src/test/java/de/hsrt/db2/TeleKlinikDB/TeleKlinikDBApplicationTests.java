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

		System.out.println("Created chat: " + result + "of GP: " + gpID + " and pat: " + patID);
	}

	@Test
	void createInvalidUserTest() {
		TeleKlinikCommand cmd = new CreateUser(
				"", // Invalid first name
				"Mustermann",
				"ABCDE",
				"M",
				new Date(1999, 01, 01)
		);

		// consultService.processTeleKlinikCommand(cmd);
		assertThrows(Exception.class, () -> consultService.processTeleKlinikCommand(cmd));
	}

	@Test
	void createUserWithFutureBirthdateTest() {
		TeleKlinikCommand cmd = new CreateUser(
				"Max",
				"Mustermann",
				"ABCDE",
				"M",
				new Date(Date.from(Instant.now().plusSeconds(60 * 60 * 24 * 365)).getTime()) // Some future date
		);

		// consultService.processTeleKlinikCommand(cmd);
		assertThrows(Exception.class, () -> consultService.processTeleKlinikCommand(cmd));

	}

	@Test
	void findChatByPatientTest() {
		// Create fake patient for testing
		Patient patient = new Patient();
		patient.setName = ("Max");
		patient.setLastName =("Mustermann");
		patient.setGender("M");
		patient.setBirthdate(new Date(2002, 04, 29));
		patient.setId(123456); // Can also use UUID.randomUUID()

		// Create fake chats
		Chat chat1 = new Chat();
		chat1.setPatient(patient);

		chat1.setGp(new GP());
		chat1.setChatState(ChatState.Active);

		chatRepo.save(chat1);

		List<Chat> chats = chatRepo.findByPatient(patient);

		assertFalse(chats.isEmpty(), "Expected at least one chat for the patient");
		assertEquals(1, chats.size(), "Expected exactly one chat for the patient");
		assertTrue(chats.contains(chat1), "Expected chat1 to be in the list of chats for the patient");
	}

	@Test
	void sendMsgRequiresContent() {
		assertThrows(IllegalArgumentException.class, () ->
				new SendMsg(UUID.randomUUID(), "", null, UUID.randomUUID()));
	}

	@Test
	void editMsgRequiresText() {
		assertThrows(IllegalArgumentException.class, () ->
				new EditMsg(UUID.randomUUID(), null));
	}
}
