package co.edu.uniquindio.agenda.models.enums;

import lombok.Getter;

@Getter
public enum Turno {

    MANIANA("Turno de la mañana"),
    TARDE("Turno de la tarde");

    private final String value;
    Turno(String value){
        this.value = value;
    }
}
