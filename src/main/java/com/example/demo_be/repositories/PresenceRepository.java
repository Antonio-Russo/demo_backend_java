package com.example.demo_be.repositories;

import com.example.demo_be.entities.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface PresenceRepository extends JpaRepository<Presence, Integer> {

    Presence findFirstByIdTelegram(int idTelegram);
    //List<Presence> findAllByIdTelegramAndDateOfPresence(int idTelegram, Date dateOfpresence);

    @Modifying
    @Transactional
    void deleteAllByIdTelegramAndDateOfPresence(int idTelegram, Date dateOfPresence);

    @Query("select e from Presence e where e.idTelegram= ?1 and year(e.dateOfPresence) = ?2 and month(e.dateOfPresence) = ?3 order by e.dateOfPresence asc")
    List<Presence> getAllPresenceByIdTelegramYearAndMonth(int idTelegram, int year, int month);
}
