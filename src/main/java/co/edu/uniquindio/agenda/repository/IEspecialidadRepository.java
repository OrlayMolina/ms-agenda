package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.models.documents.Especialidad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEspecialidadRepository extends MongoRepository<Especialidad, String> {

}
