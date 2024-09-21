package co.edu.uniquindio.agenda.dto.agenda;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.util.List;

public record CrearAgendaDTO(
        @NotNull List<ObjectId> idMedicos,
        @NotBlank String especialidad,
        @NotBlank String duracion) {
}
