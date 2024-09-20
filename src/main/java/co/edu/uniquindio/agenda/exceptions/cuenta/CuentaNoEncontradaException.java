package co.edu.uniquindio.agenda.exceptions.cuenta;

import co.edu.uniquindio.agenda.models.enums.TipoError;

public class CuentaNoEncontradaException extends Exception{

    private final TipoError tipoError;

    public CuentaNoEncontradaException(String mensaje){
        super(mensaje);
        this.tipoError = TipoError.UNKNOWN_ERROR;
    }

    public CuentaNoEncontradaException(String mensaje, TipoError tipoError) {
        super(mensaje);
        this.tipoError = tipoError;
    }

    public CuentaNoEncontradaException(String mensaje, TipoError tipoError, Throwable causa) {
        super(mensaje, causa);
        this.tipoError = tipoError;
    }

    public TipoError obtenerTipoError(){
        return tipoError;
    }
}
