package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.models.documents.Notificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificacionRepository extends MongoRepository<Notificacion, String> {
}
