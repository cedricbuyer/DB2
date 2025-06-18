package de.hsrt.db2.TeleKlinikDB;

import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommand;
import de.hsrt.db2.TeleKlinikDB.commands.TeleKlinikCommandResult;
import de.hsrt.db2.TeleKlinikDB.commands.user.CreateUser;
import de.hsrt.db2.TeleKlinikDB.enums.UserType;
import de.hsrt.db2.TeleKlinikDB.service.TeleKlinikDBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

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
}
