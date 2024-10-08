package co.edu.uniquindio.agenda.controllers.exceptions.agenda;

import co.edu.uniquindio.agenda.models.enums.TipoError;

public class AgendaNoCreadaException extends Exception{

    private final TipoError tipoError;

    public AgendaNoCreadaException(String mensaje){
        super(mensaje);
        this.tipoError = TipoError.UNKNOWN_ERROR;
    }

    public AgendaNoCreadaException(String mensaje, TipoError tipoError) {
        super(mensaje);
        this.tipoError = tipoError;
    }

    public AgendaNoCreadaException(String mensaje, TipoError tipoError, Throwable causa) {
        super(mensaje, causa);
        this.tipoError = tipoError;
    }

    public TipoError obtenerTipoError(){
        return tipoError;
    }
}
