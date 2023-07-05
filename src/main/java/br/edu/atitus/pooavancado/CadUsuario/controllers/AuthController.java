package br.edu.atitus.pooavancado.CadUsuario.controllers;

import java.security.SecureRandom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.pooavancado.CadUsuario.Entities.People;
import br.edu.atitus.pooavancado.CadUsuario.comum.JwtUtils;
import br.edu.atitus.pooavancado.CadUsuario.controllers.payloads.LoginPayload;
import br.edu.atitus.pooavancado.CadUsuario.controllers.payloads.SignupPayload;
import br.edu.atitus.pooavancado.CadUsuario.services.PeopleService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private final PeopleService peopleService;
	public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
			PeopleService peopleService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.peopleService = peopleService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody SignupPayload signup) {
		People peopleNew = new People();
		peopleNew.setNome(signup.getNome());
		peopleNew.setEmail(signup.getEmail());
		peopleNew.setDepartamento(signup.getDepartamento());
		peopleNew.setStatus(true);
		String password = gerarSenhaAleatoria(10);
		peopleNew.setPassword(new BCryptPasswordEncoder().encode(password));
		
		try {
			peopleService.save(peopleNew);
			return ResponseEntity.status(HttpStatus.OK).body(password);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		
	}
	
	private String gerarSenhaAleatoria(int tamanho) {
		String CARACTERES_PERMITIDOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder(tamanho);

        for (int i = 0; i < tamanho; i++) {
            int indice = random.nextInt(CARACTERES_PERMITIDOS.length());
            senha.append(CARACTERES_PERMITIDOS.charAt(indice));
        }
        return senha.toString();
    }


	@PostMapping("/signin")
	public ResponseEntity<Object> signin(@RequestBody LoginPayload login) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String tokenJwt = jwtUtils.generateTokenFromEmail(login.getEmail());
		return ResponseEntity.ok().body(tokenJwt);
		
	}

}
