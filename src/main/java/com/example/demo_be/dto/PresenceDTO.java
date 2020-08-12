package com.example.demo_be.dto;

import com.example.demo_be.entities.Presence;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode
public class PresenceDTO implements Serializable {
    private static final long serialVersionUID = 7234426374848359597L;

    @Range(min = 1)
    private int idTelegram;

    @NotNull(message = "deve essere inviato")
    @NotBlank(message = "non può essere vuoto")
    private String name;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Prego inserire una data valida in formato yyyy-MM-dd.")
    @Pattern(regexp = "^(19|20)\\d\\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])", message = "inserire data formato yyyy-MM-dd")
    private String dateOfPresence;

    @Range(min=1, max=12)
    private int workingHours;



    /*
    @JsonCreator
    public PresenceDTO(@JsonProperty("idTelegram") int idTelegram,
                       @JsonProperty("dataCreation") Date dataCreation,
                       @JsonProperty("name") String name,
                       @JsonProperty("dateOfPresence") Date dateOfPresence,
                       @JsonProperty("workingHours") int workingHours
                       ) {
        this.idTelegram = idTelegram;
        this.dataCreation = dataCreation;
        this.name = name;
        this.dateOfPresence = dateOfPresence;
        this.workingHours = workingHours;
    }
    public static PresenceDTO fromEntity(Presence n) {
        PresenceDTO dto = new PresenceDTO(n.getIdTelegram(),n.getDataCreation(),n.getName(),n.getDateOfPresence(),n.getWorkingHours());
        return dto;
    }
    */

    public Presence toEntity() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Presence result = new Presence();
        result.setIdTelegram(idTelegram);
        result.setDataCreation(new Date());
        result.setName(name);
        result.setDateOfPresence(formatter.parse(dateOfPresence));
        result.setWorkingHours(workingHours);
        return result;
    }

    public static PresenceDTO fromEntity(Presence n) {
        PresenceDTO dto = new PresenceDTO();
        dto.idTelegram=n.getIdTelegram();
        dto.name=n.getName();
        dto.dateOfPresence=n.getDateOfPresence().toString();
        dto.workingHours=n.getWorkingHours();
        return dto;
    }
}
