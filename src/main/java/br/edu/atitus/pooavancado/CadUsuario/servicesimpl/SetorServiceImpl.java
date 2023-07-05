package br.edu.atitus.pooavancado.CadUsuario.servicesimpl;

import org.springframework.stereotype.Service;

import br.edu.atitus.pooavancado.CadUsuario.repositories.SetorRepository;
import br.edu.atitus.pooavancado.CadUsuario.services.SetorService;

@Service
public class SetorServiceImpl implements SetorService {

	final SetorRepository setorRepository;
	public SetorServiceImpl(SetorRepository setorRepository) {
		super();
		this.setorRepository = setorRepository;
	}
	
	@Override
	public SetorRepository getRepository() {
		return setorRepository;
	}
	
}
