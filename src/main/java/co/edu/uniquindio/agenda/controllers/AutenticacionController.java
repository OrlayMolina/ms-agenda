package co.edu.uniquindio.agenda.controllers;

import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.CuentaNoCreadaException;
import co.edu.uniquindio.agenda.dto.cuenta.*;
import co.edu.uniquindio.agenda.services.interfaces.IAutenticacionService;
import co.edu.uniquindio.agenda.services.interfaces.ICuentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    private final IAutenticacionService autenticacionService;
    private final ICuentaService cuentaService;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionCliente(@Valid @RequestBody
                                                                     LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = autenticacionService.iniciarSesion(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/crear-paciente")
    public ResponseEntity<MensajeDTO<String>> crearCuentaPaciente(@Valid @RequestBody CrearCuentaPacienteDTO cuentaPacienteDTO) throws CuentaNoCreadaException {
        try {
            cuentaService.crearCuentaPaciente(cuentaPacienteDTO);
            return ResponseEntity.ok().body( new MensajeDTO<>( false, "El paciente fue creado exitosamente."));
        } catch (Exception e){
            throw new CuentaNoCreadaException("El paciente no fue creado " + e.getMessage());
        }
    }

    @PostMapping("/crear-profesional")
    public ResponseEntity<MensajeDTO<String>> crearCuentaProfesional(@Valid @RequestBody CrearCuentaProfesionalDTO cuentaProfesionalDTO) throws CuentaNoCreadaException {
        try {
            cuentaService.crearCuentaProfesional(cuentaProfesionalDTO);
            return ResponseEntity.ok().body( new MensajeDTO<>( false, "El profesional fue creado exitosamente."));
        } catch (Exception e){
            throw new CuentaNoCreadaException("El profesional no fue creado " + e.getMessage());
        }
    }
}
