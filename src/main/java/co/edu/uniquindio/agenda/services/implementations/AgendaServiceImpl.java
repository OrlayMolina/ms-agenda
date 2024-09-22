package co.edu.uniquindio.agenda.services.implementations;

import co.edu.uniquindio.agenda.dto.agenda.CrearAgendaDTO;
import co.edu.uniquindio.agenda.exceptions.agenda.AgendaNoCreadaException;
import co.edu.uniquindio.agenda.models.documents.Agenda;
import co.edu.uniquindio.agenda.models.enums.EstadoAgenda;
import co.edu.uniquindio.agenda.repository.IAgendaRepository;
import co.edu.uniquindio.agenda.services.interfaces.IAgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgendaServiceImpl implements IAgendaService {

    private final IAgendaRepository agendaRepository;

    @Override
    public String crearAgenda(CrearAgendaDTO crearAgendaDTO) throws AgendaNoCreadaException {
        try {
            Agenda agenda = new Agenda();

            agenda.setIdMedico( crearAgendaDTO.idMedicos() );
            agenda.setEspecialidad( crearAgendaDTO.especialidad());
            agenda.setDuracion( crearAgendaDTO.duracion() );
            agenda.setEstado( EstadoAgenda.ACTIVA );

            agendaRepository.save( agenda );

            return "Agenda creada exitosamente.";
        } catch( Exception e){
            throw new AgendaNoCreadaException("La agenda no fuie creada. " + e.getMessage());
        }
    }
}
