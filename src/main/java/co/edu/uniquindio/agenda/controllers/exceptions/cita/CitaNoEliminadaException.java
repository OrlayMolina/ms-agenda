package co.edu.uniquindio.agenda.controllers.exceptions.cita;

import co.edu.uniquindio.agenda.models.enums.TipoError;

public class CitaNoEliminadaException extends Exception{

    private final TipoError tipoError;

    public CitaNoEliminadaException(String mensaje){
        super(mensaje);
        this.tipoError = TipoError.UNKNOWN_ERROR;
    }

    public CitaNoEliminadaException(String mensaje, TipoError tipoError) {
        super(mensaje);
        this.tipoError = tipoError;
    }

    public CitaNoEliminadaException(String mensaje, TipoError tipoError, Throwable causa) {
        super(mensaje, causa);
        this.tipoError = tipoError;
    }

    public TipoError obtenerTipoError(){
        return tipoError;
    }
}
