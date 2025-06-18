package de.hsrt.db2.TeleKlinikDB.service;

import de.hsrt.db2.TeleKlinikDB.repo.ChatRepo;
import de.hsrt.db2.TeleKlinikDB.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeleConsultService {
    @Autowired
    public MessageRepo messageRepo;

    @Autowired
    public ChatRepo chatRepo;
}
