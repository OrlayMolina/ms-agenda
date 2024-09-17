package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.models.documents.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends MongoRepository<Cuenta, String> {
}
