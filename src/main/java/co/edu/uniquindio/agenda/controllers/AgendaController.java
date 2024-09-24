package co.edu.uniquindio.agenda.controllers;

import co.edu.uniquindio.agenda.dto.agenda.InformacionAgendaDTO;
import co.edu.uniquindio.agenda.dto.cuenta.MensajeDTO;
import co.edu.uniquindio.agenda.controllers.exceptions.agenda.AgendaNoEncontradaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.ProfesionalesNoEncontradosException;
import co.edu.uniquindio.agenda.controllers.exceptions.especialidad.EspecialidadNoEncontradaException;
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

    @GetMapping("/obtener-agendas-especialidad/{idEspecilidad}")
    public ResponseEntity<MensajeDTO<InformacionAgendaDTO>> obtenerAgendasPorEspecialidad(@PathVariable String idEspecilidad ) throws AgendaNoEncontradaException, EspecialidadNoEncontradaException, ProfesionalesNoEncontradosException {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, agendaService.obtenerInformacionAgendaPorEspecialidadDTO( idEspecilidad )));
    }
}
