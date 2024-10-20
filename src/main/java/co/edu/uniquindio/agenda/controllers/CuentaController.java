package co.edu.uniquindio.agenda.controllers;

import co.edu.uniquindio.agenda.controllers.exceptions.agenda.AgendaNoEncontradaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cita.CitaNoCreadaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cita.PacienteNoAfiliadoException;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.*;
import co.edu.uniquindio.agenda.controllers.exceptions.especialidad.EspecialidadNoEncontradaException;
import co.edu.uniquindio.agenda.dto.agenda.InformacionAgendaDTO;
import co.edu.uniquindio.agenda.dto.cita.CrearCitaDTO;
import co.edu.uniquindio.agenda.dto.cuenta.*;
import co.edu.uniquindio.agenda.services.interfaces.IAgendaService;
import co.edu.uniquindio.agenda.services.interfaces.ICitaService;
import co.edu.uniquindio.agenda.services.interfaces.ICuentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuentas")
@SecurityRequirement(name = "bearerAuth")
public class CuentaController {

    private final ICuentaService cuentaService;
    private final IAgendaService agendaService;
    private final ICitaService citaService; 

    @Operation(summary = "Listar Profesionales", description = "Permite acceder a todos los atributos del profesional creado en la clínica")
    @GetMapping("/listar-profesionales")
    public ResponseEntity<MensajeDTO<List<ItemProfesionalDTO>>> listarProfesionales() throws ProfesionalesNoEncontradosException {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, cuentaService.listarProfesionales()) );
    }

    @Operation(summary = "Crear cuenta", description = "Permite crear una cuenta de paciente")
    @PostMapping("/crear-paciente")
    public ResponseEntity<MensajeDTO<String>> crearCuentaPaciente(@Valid @RequestBody CrearCuentaPacienteDTO cuentaPacienteDTO) throws CuentaNoCreadaException {
        try {
            cuentaService.crearCuentaPaciente(cuentaPacienteDTO);
            return ResponseEntity.ok().body( new MensajeDTO<>( false, "El paciente fue creado exitosamente."));
        } catch (Exception e){
            throw new CuentaNoCreadaException("El paciente no fue creado " + e.getMessage());
        }
    }

    @GetMapping("/obtener-agendas/{idAgenda}")
    public ResponseEntity<MensajeDTO<InformacionAgendaDTO>> obtenerAgendas(@PathVariable String idAgenda) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException {
        return ResponseEntity.ok().body( new MensajeDTO<>( false, agendaService.obtenerInformacionAgendaDTO( idAgenda )) );
    }

    @PostMapping("/obtener-agendas-especialidad")
    public ResponseEntity<MensajeDTO<InformacionAgendaDTO>> obtenerAgendasPorEspecialidad(@RequestBody String especialidad ) throws AgendaNoEncontradaException, EspecialidadNoEncontradaException, ProfesionalesNoEncontradosException {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, agendaService.obtenerInformacionAgendaPorEspecialidadDTO( especialidad )));
    }

    @PostMapping("/obtener-agendas-profesional")
    public ResponseEntity<MensajeDTO<InformacionAgendaDTO>> obtenerAgendasPorProfesional(@RequestBody String nombreProfesional ) throws AgendaNoEncontradaException, EspecialidadNoEncontradaException, ProfesionalesNoEncontradosException, CuentaNoEncontradaException {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, agendaService.obtenerInformacionAgendaPorMedicoDTO( nombreProfesional )));
    }

    @PostMapping("/citas/crear-cita")
    public ResponseEntity<MensajeDTO<String>> crearCitas(@RequestBody CrearCitaDTO crearCitaDTO) throws CitaNoCreadaException, PacienteNoAfiliadoException {
        return ResponseEntity.ok().body( new MensajeDTO<>( false, citaService.crearCitaMedica(crearCitaDTO)));
    }

}
