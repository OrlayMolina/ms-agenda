package co.edu.uniquindio.agenda.controllers;

import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.*;
import co.edu.uniquindio.agenda.dto.cuenta.*;
import co.edu.uniquindio.agenda.services.interfaces.ICuentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuentas")
@SecurityRequirement(name = "bearerAuth")
public class CuentaController {

    private final ICuentaService cuentaService;

    @Operation(summary = "Listar Profesionales", description = "Permite acceder a todos los atributos del profesional creado en la clínica")
    @GetMapping("/listar-profesionales")
    public ResponseEntity<MensajeDTO<List<ItemProfesionalDTO>>> listarProfesionales() throws ProfesionalesNoEncontradosException {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, cuentaService.listarProfesionales()) );
    }

}
