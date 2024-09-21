package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.models.documents.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICitaRepository extends MongoRepository<Cita, String> {

    Optional<List<Cita>> findCitasByFechaCita(LocalDateTime fechaCita);
    Optional<List<Cita>> findCitasByIdMedico(String id);
    Optional<List<Cita>> findCitasByEspecilidad(String especialidad);
}
