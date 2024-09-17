package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.models.documents.Sede;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends MongoRepository<Sede, String> {
}
