package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.models.documents.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICitaRepository extends MongoRepository<Cita, String> {

    @Query("{ 'cita': { $ne: null }, 'cita.fechaCita': ?0 }")
    Optional<List<Cita>> findCitasByFechaCita(LocalDateTime fechaCita);

    @Query("{ 'cita': { $ne: null }, 'cita.idMedico': ?0 }")
    Optional<List<Cita>> findCitasByIdMedico(String id);
}
