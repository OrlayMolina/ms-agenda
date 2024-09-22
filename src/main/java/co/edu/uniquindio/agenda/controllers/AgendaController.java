package co.edu.uniquindio.agenda.controllers;

import co.edu.uniquindio.agenda.dto.agenda.InformacionAgendaDTO;
import co.edu.uniquindio.agenda.dto.cita.InformacionCitaDTO;
import co.edu.uniquindio.agenda.dto.cuenta.MensajeDTO;
import co.edu.uniquindio.agenda.exceptions.agenda.AgendaNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.cita.CitaNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.cita.PacienteNoAfiliadoException;
import co.edu.uniquindio.agenda.exceptions.cuenta.ProfesionalesNoEncontradosException;
import co.edu.uniquindio.agenda.exceptions.sede.SedeNoEncontradaException;
import co.edu.uniquindio.agenda.services.interfaces.IAgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agendas")
public class AgendaController {

    private final IAgendaService agendaService;

    @GetMapping("/obtener-agendas/{idAgenda}")
    public ResponseEntity<MensajeDTO<InformacionAgendaDTO>> obtenerAgendas(@PathVariable String idAgenda) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException {
        return ResponseEntity.ok().body( new MensajeDTO<>( false, agendaService.obtenerInformacionAgendaDTO( idAgenda )) );
    }
}
