package co.edu.uniquindio.agenda.controllers;

import co.edu.uniquindio.agenda.dto.cita.InformacionCitaDTO;
import co.edu.uniquindio.agenda.dto.cuenta.MensajeDTO;
import co.edu.uniquindio.agenda.controllers.exceptions.cita.CitaNoEncontradaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cita.PacienteNoAfiliadoException;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.ProfesionalesNoEncontradosException;
import co.edu.uniquindio.agenda.controllers.exceptions.sede.SedeNoEncontradaException;
import co.edu.uniquindio.agenda.services.interfaces.ICitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/citas")
public class CitaController {

    private final ICitaService citaService;

    @GetMapping("/obtener-cita/{idCita}")
    public ResponseEntity<MensajeDTO<InformacionCitaDTO>> obtenerCitas(@PathVariable String idCita) throws SedeNoEncontradaException, PacienteNoAfiliadoException, ProfesionalesNoEncontradosException, CitaNoEncontradaException {
        return ResponseEntity.ok().body( new MensajeDTO<>( false, citaService.obtenerInformacionCitaDTO( idCita )) );
    }

}
