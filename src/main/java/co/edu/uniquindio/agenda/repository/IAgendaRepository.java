package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.models.documents.Agenda;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgendaRepository extends MongoRepository<Agenda, String> {
}
