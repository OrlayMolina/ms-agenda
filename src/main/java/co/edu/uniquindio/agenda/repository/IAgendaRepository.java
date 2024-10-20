package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.controllers.exceptions.agenda.AgendaNoEncontradaException;
import co.edu.uniquindio.agenda.models.documents.Agenda;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAgendaRepository extends MongoRepository<Agenda, String> {

    Optional<Agenda> findAgendaByEspecialidad(String especialidad ) throws AgendaNoEncontradaException;
    Optional<Agenda> findAgendaByIdMedico(List<ObjectId> idMedico);
}
