package de.hsrt.db2.TeleKlinikDB;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommand;
import de.hsrt.db2.TeleKlinikDB.commands.user.CreateUser;
import de.hsrt.db2.TeleKlinikDB.service.TeleConsultService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.sql.Date;

@SpringBootTest
class TeleKlinikDBApplicationTests {
	@Autowired
	TeleConsultService consultService;

	@Test
	void createUserTest() {
		TeleKlinikCommand cmd = new CreateUser(
				"Max",
				"Mustermann",
				"ABCDE",
				"M",
				new Date(2002, 04, 29)
		);

		consultService.processTeleKlinikCommand(cmd);
	}

	@Test
	void createInvalidUserTest() {
		TeleklinikCommand cmd = new CreateUser(
				"", // Invalid first name
				"Mustermann",
				"ABCDE",
				"M",
				new Date(1999, 01, 01)
		);

		try {
			consultService.processTeleKlinikCommand(cmd);
		} catch (Exception e) {
			System.out.println("Expected exception for invalid user creation: " + e.getMessage());
		}
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
		try {
			consultService.processTeleKlinikCommand(cmd);
		} catch (Exception e) {
			System.out.println("Expected exception for user with future birthdate: " + e.getMessage());
		}
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
}
