package com.example.demo_be.services;

import com.example.demo_be.entities.Presence;
import com.example.demo_be.repositories.PresenceRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
@SpringBootTest
class PresenceServiceTest {

    @Autowired
    private PresenceService service;

    @Autowired
    private PresenceRepository repository;

    @Test
    void testGetAllPresenceByIdTelegramYearAndMonth() {
        List<Presence> presenze = service.getAllPresenceByIdTelegramYearAndMonth(111,2020,8);
        Assert.assertNotNull(presenze);
        Assert.assertEquals(2,presenze.size());
    }
}