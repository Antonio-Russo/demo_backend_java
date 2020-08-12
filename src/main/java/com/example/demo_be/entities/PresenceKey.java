package com.example.demo_be.entities;

import java.io.Serializable;
import java.util.Date;

//chiave composita sulla tabella Presence
public class PresenceKey implements Serializable {
    private int idTelegram;
    private Date dateOfPresence;
}
