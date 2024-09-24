package co.edu.uniquindio.agenda.repository;

import co.edu.uniquindio.agenda.controllers.exceptions.agenda.AgendaNoEncontradaException;
import co.edu.uniquindio.agenda.models.documents.Agenda;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAgendaRepository extends MongoRepository<Agenda, String> {

    Optional<Agenda> findAgendaByEspecialidad(String especialidad ) throws AgendaNoEncontradaException;
}
