package br.edu.atitus.pooavancado.CadUsuario.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.pooavancado.CadUsuario.Entities.Mercadoria;
import br.edu.atitus.pooavancado.CadUsuario.services.GenericService;
import br.edu.atitus.pooavancado.CadUsuario.services.MercadoriaService;

@RestController
@RequestMapping("/mercadorias")
public class MercadoriaController extends GenericController<Mercadoria> {

	final MercadoriaService mercadoriaService;
	public MercadoriaController(MercadoriaService mercadoriaService) {
		super();
		this.mercadoriaService = mercadoriaService;
	}

	@Override
	GenericService<Mercadoria> getService() {
		return mercadoriaService;
	}

}
