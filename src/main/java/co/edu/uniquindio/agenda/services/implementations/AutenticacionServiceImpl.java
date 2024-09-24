package co.edu.uniquindio.agenda.services.implementations;

import co.edu.uniquindio.agenda.config.JWTUtils;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.SesionNoIniciadaException;
import co.edu.uniquindio.agenda.dto.cuenta.LoginDTO;
import co.edu.uniquindio.agenda.dto.cuenta.TokenDTO;
import co.edu.uniquindio.agenda.models.documents.Cuenta;
import co.edu.uniquindio.agenda.repository.ICuentaRepository;
import co.edu.uniquindio.agenda.services.interfaces.IAutenticacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacionServiceImpl implements IAutenticacionService {

    private final ICuentaRepository cuentaRepository;
    private final CuentaServiceImpl cuentaService;
    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO iniciarSesion(LoginDTO loginDTO) throws SesionNoIniciadaException {
        try {
            Cuenta cuenta = obtenerPorEmail(loginDTO.email());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


            if( !passwordEncoder.matches(loginDTO.password(), cuenta.getPassword()) ) {
                throw new Exception("La contraseña es incorrecta");
            }


            Map<String, Object> map = cuentaService.construirClaims(cuenta);
            return new TokenDTO( jwtUtils.generarToken(cuenta.getEmail(), map) );

        }catch(Exception e){
            throw new SesionNoIniciadaException("Sesión no fue iniciada. " + e.getMessage());
        }
    }

    private Cuenta obtenerPorEmail(String email) throws Exception{
        try {
            Optional<Cuenta> cuenta = cuentaRepository.findByEmail( email );

            if( cuenta.isEmpty() ){
                throw new Exception("Cuenta no encontrado.");
            }

            return cuenta.get();
        }catch(Exception e){
            throw new Exception("Error al buscar el correo." + e.getMessage());
        }
    }
}
