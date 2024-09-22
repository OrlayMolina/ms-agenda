package co.edu.uniquindio.agenda.services.implementations;

import co.edu.uniquindio.agenda.dto.agenda.CrearAgendaDTO;
import co.edu.uniquindio.agenda.dto.agenda.InformacionAgendaDTO;
import co.edu.uniquindio.agenda.dto.cita.InformacionCitaDTO;
import co.edu.uniquindio.agenda.exceptions.agenda.AgendaNoCreadaException;
import co.edu.uniquindio.agenda.exceptions.agenda.AgendaNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.cita.CitaNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.cita.PacienteNoAfiliadoException;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.cuenta.ProfesionalesNoEncontradosException;
import co.edu.uniquindio.agenda.exceptions.sede.SedeNoEncontradaException;
import co.edu.uniquindio.agenda.models.documents.Agenda;
import co.edu.uniquindio.agenda.models.documents.Cita;
import co.edu.uniquindio.agenda.models.documents.Sede;
import co.edu.uniquindio.agenda.models.enums.EstadoAgenda;
import co.edu.uniquindio.agenda.models.vo.Profesional;
import co.edu.uniquindio.agenda.models.vo.Usuario;
import co.edu.uniquindio.agenda.repository.IAgendaRepository;
import co.edu.uniquindio.agenda.services.interfaces.IAgendaService;
import co.edu.uniquindio.agenda.services.interfaces.ICitaService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgendaServiceImpl implements IAgendaService {

    private final IAgendaRepository agendaRepository;
    private final CitaServiceImpl citaService;

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

    @Override
    public InformacionAgendaDTO obtenerInformacionAgendaDTO(String idAgenda) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException {
        try {

            Agenda agenda = encontrarAgendaPorId( idAgenda );
            List<String> profesionales = new ArrayList<>();
            List<InformacionCitaDTO> citas = new ArrayList<>();
            for(ObjectId profesionalConsecutivo : agenda.getIdMedico()){
                Profesional profesional = citaService.encontrarProfesionalPorId(String.valueOf(profesionalConsecutivo));
                profesionales.add( profesional.getNombres() + " " + profesional.getApellidos() );
                citas.addAll( citaService.citasPorMedico( profesionalConsecutivo.toHexString() ) );
            }

            ObjectId objectIdAgenda = new ObjectId( agenda.getId() );

            InformacionAgendaDTO infoAgenda = new InformacionAgendaDTO(
                    objectIdAgenda,
                    profesionales,
                    agenda.getEspecialidad(),
                    agenda.getDuracion(),
                    citas
            );

            return infoAgenda;

        } catch (AgendaNoEncontradaException | ProfesionalesNoEncontradosException e) {
            throw e;
        } catch (CitaNoEncontradaException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InformacionAgendaDTO obtenerInformacionAgendaPorEspecialidadDTO(String idEspecialidad) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException {
        return null;
    }

    @Override
    public InformacionAgendaDTO obtenerInformacionAgendaPorMedicoDTO(String idMedico) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException {
        return null;
    }

    private Agenda encontrarAgendaPorId(String id) throws AgendaNoEncontradaException {
        try {
            Optional<Agenda> agenda = agendaRepository.findById( id );

            if( agenda.isEmpty() ){
                throw new AgendaNoEncontradaException("La Agenda no se encuentra registrada en la Clínica.");
            }

            return agenda.get();

        } catch (Exception e){
            throw new AgendaNoEncontradaException("La Agenda no fue encontrada " + e.getMessage());
        }
    }
}
