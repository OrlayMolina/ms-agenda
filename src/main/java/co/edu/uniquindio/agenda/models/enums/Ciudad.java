package co.edu.uniquindio.agenda.models.enums;

import lombok.Getter;

@Getter
public enum Ciudad {
    ARMENIA("Armenia"),
    CALARCA("Calarcá"),
    MANIZALES("Manizales"),
    PEREIRA("Pereira"),
    DOSQUEBRADAS("Dosquebradas");

    private final String value;
    Ciudad(String value){
        this.value = value;
    }
}
