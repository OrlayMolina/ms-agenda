package co.edu.uniquindio.agenda.models.enums;

import lombok.Getter;

@Getter
public enum Departamento {
    CALDAS("Caldas") {
        @Override
        public Ciudad[] getCiudades() {
            return new Ciudad[] { Ciudad.PEREIRA, Ciudad.DOSQUEBRADAS };
        }
    },
    RISARALDA("Risaralda") {
        @Override
        public Ciudad[] getCiudades() {
            return new Ciudad[] { Ciudad.PEREIRA, Ciudad.DOSQUEBRADAS };
        }
    },
    QUINDIO("Quindío") {
        @Override
        public Ciudad[] getCiudades() {
            return new Ciudad[] { Ciudad.ARMENIA, Ciudad.CALARCA };
        }
    };

    private final String nombre;

    Departamento(String nombre) {
        this.nombre = nombre;
    }

    public abstract Ciudad[] getCiudades();
}
