package co.edu.uniquindio.agenda.models.enums;

import lombok.Getter;

@Getter
public enum Consultorio {

    UNO("001"),
    DOS("002"),
    TRES("003"),
    CUATRO("004"),
    CINCO("005");

    private final String value;
    Consultorio(String value){
        this.value = value;
    }
}
