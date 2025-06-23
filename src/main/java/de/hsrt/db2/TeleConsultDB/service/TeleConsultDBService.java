package de.hsrt.db2.TeleConsultDB.service;

import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommand;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultCommandResult;
import de.hsrt.db2.TeleConsultDB.commands.TeleConsultContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeleConsultDBService {
    /**
     * Repos are automatically injected via the generated
     * constructor from @RequiredArgsConstructor.
     * See the
     * <a href="https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html">
     *      Spring Documentation regarding injection
     * </a> for more.
     */
    private final TeleConsultContext teleConsultContext;

    public TeleConsultCommandResult processTeleConsultCommand(TeleConsultCommand command) {
        return command.execute(teleConsultContext);
    }
}
