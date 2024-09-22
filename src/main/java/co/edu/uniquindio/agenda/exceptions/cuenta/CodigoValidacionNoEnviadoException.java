package co.edu.uniquindio.agenda.exceptions.cuenta;

import co.edu.uniquindio.agenda.models.enums.TipoError;

public class CodigoValidacionNoEnviadoException extends Exception{

    private final TipoError tipoError;

    public CodigoValidacionNoEnviadoException(String mensaje){
        super(mensaje);
        this.tipoError = TipoError.UNKNOWN_ERROR;
    }

    public CodigoValidacionNoEnviadoException(String mensaje, TipoError tipoError) {
        super(mensaje);
        this.tipoError = tipoError;
    }

    public CodigoValidacionNoEnviadoException(String mensaje, TipoError tipoError, Throwable causa) {
        super(mensaje, causa);
        this.tipoError = tipoError;
    }

    public TipoError obtenerTipoError(){
        return tipoError;
    }
}
