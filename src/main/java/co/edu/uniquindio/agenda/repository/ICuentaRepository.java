package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.models.documents.Cuenta;
import co.edu.uniquindio.agenda.models.enums.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICuentaRepository extends MongoRepository<Cuenta, String> {

    @Query("{ 'usuario': { $ne: null }, 'usuario.nroDocumento': ?0 }")
    Optional<Cuenta> buscaNroDocumento(String nroDocumento);
    Optional<Cuenta> findByEmail(String email);
    Optional<List<Cuenta>> findCuentasByRol(String rol);
    Optional<Cuenta> findCuentasByIdIs(String id);
    Optional<Cuenta> findCuentasByUsuarioNombres(String usuarioNombre);
}
