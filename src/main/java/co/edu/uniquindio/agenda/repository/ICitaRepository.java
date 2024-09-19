package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.models.documents.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitaRepository extends MongoRepository<Cita, String> {
}
