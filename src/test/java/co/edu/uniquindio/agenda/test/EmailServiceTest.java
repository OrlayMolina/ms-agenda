package co.edu.uniquindio.agenda.test;

import co.edu.uniquindio.agenda.dto.email.EmailDTO;
import co.edu.uniquindio.agenda.services.interfaces.IEmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private IEmailService emailService;

    @Test
    public void enviarCorreoTest() {

    }
}
