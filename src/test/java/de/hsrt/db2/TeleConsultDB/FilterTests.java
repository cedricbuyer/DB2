package de.hsrt.db2.TeleConsultDB;

import de.hsrt.db2.TeleConsultDB.commands.chat.CreateChat;
import de.hsrt.db2.TeleConsultDB.commands.user.CreateUser;
import de.hsrt.db2.TeleConsultDB.enums.UserType;
import de.hsrt.db2.TeleConsultDB.model.Chat;
import de.hsrt.db2.TeleConsultDB.service.TeleConsultDBService;
import de.hsrt.db2.TeleConsultDB.service.filters.ChatByGPFilter;
import de.hsrt.db2.TeleConsultDB.service.filters.ChatByPatientFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@SpringBootTest
class FilterTests {

    @Autowired
    TeleConsultDBService consultService;

    @Test
    void filterChatByGP() {
        var gpResult = consultService.processTeleConsultCommand(new CreateUser(
                "Filter", "GP", "M", Date.valueOf(LocalDate.of(1999,1,1)),
                UserType.GP, "doc", null));
        var patientResult = consultService.processTeleConsultCommand(new CreateUser(
                "Filter", "Patient", "F", Date.valueOf(LocalDate.of(2000,1,1)),
                UserType.PATIENT, null, "AOK"));

        UUID gpId = gpResult.createdObjectID().orElseThrow();
        UUID patId = patientResult.createdObjectID().orElseThrow();

        consultService.processTeleConsultCommand(new CreateChat(gpId, patId));

        List<Chat> result = consultService.filterChats(new ChatByGPFilter(gpId));
        System.out.println(result);
    }

    @Test
    void filterChatByPatient() {
        var gpResult = consultService.processTeleConsultCommand(new CreateUser(
                "Filter2", "GP", "M", Date.valueOf(LocalDate.of(1999,1,1)),
                UserType.GP, "doc", null));
        var patientResult = consultService.processTeleConsultCommand(new CreateUser(
                "Filter2", "Patient", "F", Date.valueOf(LocalDate.of(2000,1,1)),
                UserType.PATIENT, null, "AOK"));

        UUID gpId = gpResult.createdObjectID().orElseThrow();
        UUID patId = patientResult.createdObjectID().orElseThrow();

        consultService.processTeleConsultCommand(new CreateChat(gpId, patId));

        List<Chat> result = consultService.filterChats(new ChatByPatientFilter(patId));
        System.out.println(result);
    }
}