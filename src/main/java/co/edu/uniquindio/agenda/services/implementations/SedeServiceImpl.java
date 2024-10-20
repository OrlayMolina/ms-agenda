package co.edu.uniquindio.agenda.services.implementations;

import co.edu.uniquindio.agenda.controllers.exceptions.sede.SedeNoEncontradaException;
import co.edu.uniquindio.agenda.dto.sede.SedeDTO;
import co.edu.uniquindio.agenda.models.documents.Sede;
import co.edu.uniquindio.agenda.repository.ISedeRepository;
import co.edu.uniquindio.agenda.services.interfaces.ISedeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SedeServiceImpl implements ISedeService {

    private final ISedeRepository sedeRepository;
    @Override
    public List<SedeDTO> listaSedes() throws SedeNoEncontradaException {

        List<Sede> sedes = sedeRepository.findAll();

        if(sedes.isEmpty()){
            throw new SedeNoEncontradaException("Las sedes no pudieron ser cargadas de la base de datos. ");
        }

        List<SedeDTO> sedesDTO = new ArrayList<>();
        for(Sede sede : sedes){
            SedeDTO sedeDTO = new SedeDTO(sede.getId(), sede.getNombre());
            sedesDTO.add(sedeDTO);
        }

        return sedesDTO;
    }
}
