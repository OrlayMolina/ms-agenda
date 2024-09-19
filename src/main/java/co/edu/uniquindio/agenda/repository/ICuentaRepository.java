package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.models.documents.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICuentaRepository extends MongoRepository<Cuenta, String> {

    @Query("{ 'usuario': { $ne: null }, 'usuario.nroDocumento': ?0 }")
    Optional<Cuenta> buscaNroDocumento(String nroDocumento);

    Optional<Cuenta> findByEmail(String email);
}
