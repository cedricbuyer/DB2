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
}
