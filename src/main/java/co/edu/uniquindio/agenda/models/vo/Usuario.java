package co.edu.uniquindio.agenda.models.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario {

    private String tipoDocumento;
    private String nroDocumento;
    private String direccion;
    private String nombres;
    private String apellidos;

    public Usuario(
            String tipoDocumento,
            String nroDocumento,
            String direccion,
            String nombres,
            String apellidos) {
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.direccion = direccion;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
}