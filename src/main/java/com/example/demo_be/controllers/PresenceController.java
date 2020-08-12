package com.example.demo_be.controllers;

import com.example.demo_be.dto.PresenceDTO;
import com.example.demo_be.entities.Presence;
import com.example.demo_be.models.responses.ErrorResponse;
import com.example.demo_be.services.PresenceService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PresenceController {

    @Autowired
    PresenceService service;

    @RequestMapping(value ="/presence", method = RequestMethod.POST)
    ResponseEntity<String> addPresence(@Valid @RequestBody PresenceDTO presenceDTO, HttpServletRequest request) {
        try {
            Presence presence = presenceDTO.toEntity();
            service.savePresence(presence);

            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Type","application/json")
                    .body("{ \"message\":\"Presenza registrata con successo\" }");

        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse(request.getServletPath(), "Api Error", Arrays.asList(ex.getMessage()));
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value ="/presence", method = RequestMethod.DELETE)
    ResponseEntity<String> deletePresence(@Valid @RequestBody PresenceDTO presenceDTO, HttpServletRequest request) {
        try {
            Presence presence = presenceDTO.toEntity();
            service.deletePresence(presence);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Type","application/json")
                    .body("{ \"message\":\"Presenza id "+ presence.getIdTelegram() +" del "+ formatter.format(presence.getDateOfPresence()) +" cancellata con successo\" }");

        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse(request.getServletPath(), "Api Error", Arrays.asList(ex.getMessage()));
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value ="/listpresences", method = RequestMethod.POST)
    ResponseEntity<String> listPresences(@RequestParam("idTelegram") int idTelegram, @RequestParam("year") int year, @RequestParam("month") int month, HttpServletRequest request) {
        try {
            List<Presence> presences= service.getAllPresenceByIdTelegramYearAndMonth(idTelegram,year,month);
            //mappo la lista di entities in una lista di DTO
            var presencesListDTO = presences.stream().map(e -> PresenceDTO.fromEntity(e)).collect(Collectors.toList());
            Gson gson = new Gson();
            String jsonPresences = gson.toJson(presencesListDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Type","application/json")
                    .body(jsonPresences);
        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse(request.getServletPath(), "Api Error", Arrays.asList(ex.getMessage()));
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
