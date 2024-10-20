package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.models.documents.Sede;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISedeRepository extends MongoRepository<Sede, String> {

}
