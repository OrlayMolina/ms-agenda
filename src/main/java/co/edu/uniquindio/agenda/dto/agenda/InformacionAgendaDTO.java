package co.edu.uniquindio.agenda.dto.agenda;

import co.edu.uniquindio.agenda.dto.cita.InformacionCitaDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.util.List;

public record InformacionAgendaDTO(
        @NotNull ObjectId id,
        @NotNull List<String> profesionales,
        @NotBlank String especialidad,
        @NotBlank String duracion,
        @NotNull List<InformacionCitaDTO> informacionCitaDTO) {
}
