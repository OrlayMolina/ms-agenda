package co.edu.uniquindio.agenda.controllers.exceptions.cuenta;

import co.edu.uniquindio.agenda.models.enums.TipoError;

public class CuentaNoCreadaException extends Exception{

    private final TipoError tipoError;

    public CuentaNoCreadaException(String mensaje){
        super(mensaje);
        this.tipoError = TipoError.UNKNOWN_ERROR;
    }

    public CuentaNoCreadaException(String mensaje, TipoError tipoError) {
        super(mensaje);
        this.tipoError = tipoError;
    }

    public CuentaNoCreadaException(String mensaje, TipoError tipoError, Throwable causa) {
        super(mensaje, causa);
        this.tipoError = tipoError;
    }

    public TipoError obtenerTipoError(){
        return tipoError;
    }
}
