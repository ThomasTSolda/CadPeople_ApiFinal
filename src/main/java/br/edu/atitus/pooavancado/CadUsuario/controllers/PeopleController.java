package br.edu.atitus.pooavancado.CadUsuario.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.pooavancado.CadUsuario.Entities.People;
import br.edu.atitus.pooavancado.CadUsuario.services.GenericService;
import br.edu.atitus.pooavancado.CadUsuario.services.PeopleService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/people")
public class PeopleController extends GenericController<People>{

	final PeopleService peopleService;
	public PeopleController(PeopleService peopleService) {
		super();
		this.peopleService = peopleService;
	}
	
	@Override
	GenericService<People> getService() {
		return peopleService;
	}

	@PatchMapping("/status/{id}")
	public ResponseEntity<Object> alteraStatusUsuario(@PathVariable long id) {
		try {
			peopleService.alteraStatus(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Alterated status with success for the  people with Id " + id);
	}

	

}
