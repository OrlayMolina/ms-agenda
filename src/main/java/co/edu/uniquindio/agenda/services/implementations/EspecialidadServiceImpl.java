package co.edu.uniquindio.agenda.services.implementations;

import co.edu.uniquindio.agenda.dto.especialidad.ActualizarEspecialidadDTO;
import co.edu.uniquindio.agenda.dto.especialidad.CrearEspecialidadDTO;
import co.edu.uniquindio.agenda.dto.especialidad.ItemEspecialidadDTO;
import co.edu.uniquindio.agenda.exceptions.especialidad.EspecialidadNoCreadaException;
import co.edu.uniquindio.agenda.exceptions.especialidad.EspecialidadNoEditadaException;
import co.edu.uniquindio.agenda.models.documents.Especialidad;
import co.edu.uniquindio.agenda.repository.IEspecialidadRepository;
import co.edu.uniquindio.agenda.services.interfaces.IEspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImpl implements IEspecialidadService {

    private final IEspecialidadRepository especialidadRepository;
    @Override
    public String crearEspecialidad(CrearEspecialidadDTO especialidadDTO) throws EspecialidadNoCreadaException {
        try {
            //TODO Verificar que no exista una especialidad con el mismo nombre o parecido

            Especialidad nuevaEspecialidad = new Especialidad( especialidadDTO.nombre() );
            especialidadRepository.save( nuevaEspecialidad );

            return "Especialidad creada exitosamente";
        } catch (Exception e){
            throw new EspecialidadNoCreadaException("La especialidad no fue creada " + e.getMessage());
        }
    }

    @Override
    public String editarEspecialidad(ActualizarEspecialidadDTO especialidadDTO) throws EspecialidadNoEditadaException {
        try {
            Especialidad especialidadEditada = obtenerEspecialidad( especialidadDTO.id() );

            especialidadEditada.setNombre( especialidadDTO.nombre() );

            especialidadRepository.save( especialidadEditada );

            return "La especialidad fue editada correctamente";
        } catch (Exception e){
            throw new EspecialidadNoEditadaException("No fue posible editar la Especialidad " + e.getMessage());
        }
    }

    @Override
    public List<ItemEspecialidadDTO> listarEspecilidades() {

        List<Especialidad> especiliadades = especialidadRepository.findAll();

        List<ItemEspecialidadDTO> items = new ArrayList<>();

        for( Especialidad especialidad : especiliadades ){
            items.add( new ItemEspecialidadDTO(
                    especialidad.getId(),
                    especialidad.getNombre()
            ));
        }
        return items;
    }

    private Especialidad obtenerEspecialidad(String id) throws Exception {
        Optional<Especialidad> optionalEspecialidad = especialidadRepository.findById(id);

        if(optionalEspecialidad.isEmpty()){
            throw new Exception("No se encontró la Especialidad con el id "+id);
        }

        return optionalEspecialidad.get();
    }
}
