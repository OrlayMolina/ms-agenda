package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.dto.email.EmailDTO;

public interface IEmailService {

    void enviarCorreo(EmailDTO emailDTO) throws Exception;
}
