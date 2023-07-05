package br.edu.atitus.pooavancado.CadUsuario.servicesimpl;

import org.springframework.stereotype.Service;

import br.edu.atitus.pooavancado.CadUsuario.Entities.Mercadoria;
import br.edu.atitus.pooavancado.CadUsuario.repositories.GenericRepository;
import br.edu.atitus.pooavancado.CadUsuario.repositories.MercadoriaRepository;
import br.edu.atitus.pooavancado.CadUsuario.services.MercadoriaService;

@Service
public class MercadoriaServiceImpl implements MercadoriaService{

	final MercadoriaRepository mercadoriaRepository;
	public MercadoriaServiceImpl(MercadoriaRepository mercadoriaRepository) {
		super();
		this.mercadoriaRepository = mercadoriaRepository;
	}


	@Override
	public GenericRepository<Mercadoria> getRepository() {
		return mercadoriaRepository;
	}

}
