package com.example.demo_be.services;

import com.example.demo_be.entities.Presence;
import com.example.demo_be.repositories.PresenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PresenceService {
    protected static final Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    @Autowired
    private PresenceRepository repository;

    public List<Presence> getAllPresenceByIdTelegramYearAndMonth(int idTelegram, int year, int month){
        try {
            return repository.getAllPresenceByIdTelegramYearAndMonth(idTelegram, year,month);
        } catch (Exception ex) {
            logger.error("Errore ricerca lista presenze: " + ex.getMessage());
            return null;
        }
    }

    public Presence savePresence(Presence presence) throws Exception {
        try {
            return repository.save(presence);
        } catch (Exception ex) {
            String messageEx = "Errore salvataggio presenze: " + ex.getMessage();
            logger.error(messageEx);
            throw new Exception(messageEx);
        }
    }

    public boolean deletePresence(Presence presence) throws Exception {
        try {
            repository.deleteAllByIdTelegramAndDateOfPresence(presence.getIdTelegram(), presence.getDateOfPresence());
            return true;
        } catch (Exception ex) {
            String messageEx = "Errore cancellazione presenze: " + ex.getMessage();
            logger.error(messageEx);
            throw new Exception(messageEx);
        }
    }
}
