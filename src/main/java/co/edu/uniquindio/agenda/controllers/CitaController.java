package co.edu.uniquindio.agenda.controllers;

import co.edu.uniquindio.agenda.services.interfaces.ICitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/citas")
public class CitaController {

    private final ICitaService citaService;
}
