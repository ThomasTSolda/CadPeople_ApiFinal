package br.edu.atitus.pooavancado.CadUsuario.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.pooavancado.CadUsuario.Entities.Setor;
import br.edu.atitus.pooavancado.CadUsuario.services.SetorService;
import br.edu.atitus.pooavancado.CadUsuario.services.GenericService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/setor")
public class SetorController extends GenericController<Setor>{

	final SetorService setorService;
	public SetorController(SetorService setorService) {
		super();
		this.setorService = setorService;
	}
	@Override
	GenericService<Setor> getService() {
		return setorService;
	}


}
