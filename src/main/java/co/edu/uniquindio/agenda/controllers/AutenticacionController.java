package co.edu.uniquindio.agenda.controllers;

import co.edu.uniquindio.agenda.controllers.exceptions.agenda.AgendaNoEncontradaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.CuentaNoCreadaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.ProfesionalesNoEncontradosException;
import co.edu.uniquindio.agenda.controllers.exceptions.especialidad.EspecialidadNoEncontradaException;
import co.edu.uniquindio.agenda.dto.agenda.InformacionAgendaDTO;
import co.edu.uniquindio.agenda.dto.cuenta.*;
import co.edu.uniquindio.agenda.services.interfaces.IAgendaService;
import co.edu.uniquindio.agenda.services.interfaces.IAutenticacionService;
import co.edu.uniquindio.agenda.services.interfaces.ICuentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    private final IAutenticacionService autenticacionService;
    private final IAgendaService agendaService;
    private final ICuentaService cuentaService;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionCliente(@Valid @RequestBody
                                                                     LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = autenticacionService.iniciarSesion(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/confirmar-cuenta")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@RequestBody Map<String, String> request) throws Exception {
        try {
            String email = request.get("email");
            String codigoValidacion = request.get("codigo");
            String resultado = cuentaService.activarCuenta(email, codigoValidacion);
            return ResponseEntity.ok().body(new MensajeDTO<>(true, resultado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500))
                    .body(new MensajeDTO<>(false, "Error al activar la cuenta: " + e.getMessage()));
        }
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

    @GetMapping("/obtener-agendas/{idAgenda}")
    public ResponseEntity<MensajeDTO<InformacionAgendaDTO>> obtenerAgendas(@PathVariable String idAgenda) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException {
        return ResponseEntity.ok().body( new MensajeDTO<>( false, agendaService.obtenerInformacionAgendaDTO( idAgenda )) );
    }

    @PostMapping("/obtener-agendas-especialidad")
    public ResponseEntity<MensajeDTO<InformacionAgendaDTO>> obtenerAgendasPorEspecialidad(@RequestBody String nombreEspecialidad ) throws AgendaNoEncontradaException, EspecialidadNoEncontradaException, ProfesionalesNoEncontradosException {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, agendaService.obtenerInformacionAgendaPorEspecialidadDTO( nombreEspecialidad )));
    }
}
