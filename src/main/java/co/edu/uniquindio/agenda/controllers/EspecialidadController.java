package co.edu.uniquindio.agenda.controllers;

import co.edu.uniquindio.agenda.dto.cuenta.MensajeDTO;
import co.edu.uniquindio.agenda.dto.especialidad.ActualizarEspecialidadDTO;
import co.edu.uniquindio.agenda.dto.especialidad.CrearEspecialidadDTO;
import co.edu.uniquindio.agenda.dto.especialidad.ItemEspecialidadDTO;
import co.edu.uniquindio.agenda.controllers.exceptions.especialidad.EspecialidadNoCreadaException;
import co.edu.uniquindio.agenda.controllers.exceptions.especialidad.EspecialidadNoEditadaException;
import co.edu.uniquindio.agenda.controllers.exceptions.especialidad.EspecialidadNoEncontradaException;
import co.edu.uniquindio.agenda.services.interfaces.IEspecialidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    private final IEspecialidadService especialidadService;

    @PostMapping("/crear-especialidad")
    public ResponseEntity<MensajeDTO<String>> crearEspecialidad(@Valid @RequestBody CrearEspecialidadDTO especialidadDTO) throws EspecialidadNoCreadaException {
        try {
            especialidadService.crearEspecialidad(especialidadDTO);
            return ResponseEntity.ok().body( new MensajeDTO<>( false, "La Especialidad fue creada exitosamente."));
        } catch (Exception e){
            throw new EspecialidadNoCreadaException("La Especialidad no fue creada " + e.getMessage());
        }
    }

    @PutMapping("/editar-especialidad")
    public ResponseEntity<MensajeDTO<String>> actualizarEspecialidad(@Valid @RequestBody ActualizarEspecialidadDTO actualizarEspecialidadDTO)throws EspecialidadNoEditadaException {
        try {
            especialidadService.editarEspecialidad(actualizarEspecialidadDTO);
            return ResponseEntity.ok().body( new MensajeDTO<>(false, "Especialidad actualizada correctamente") );
        }catch (Exception e){
            throw new EspecialidadNoEditadaException("La Especialidad no fue editada " + e.getMessage());
        }
    }


}
