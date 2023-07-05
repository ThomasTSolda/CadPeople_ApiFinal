package br.edu.atitus.pooavancado.CadUsuario.servicesimpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.atitus.pooavancado.CadUsuario.Entities.People;
import br.edu.atitus.pooavancado.CadUsuario.repositories.GenericRepository;
import br.edu.atitus.pooavancado.CadUsuario.repositories.PeopleRepository;
import br.edu.atitus.pooavancado.CadUsuario.services.PeopleService;

@Service
public class PeopleServiceImpl implements PeopleService{
	
	final PeopleRepository peopleRepository;
	public PeopleServiceImpl(PeopleRepository peopleRepository) {
		super();
		this.peopleRepository = peopleRepository;
	}
	
	@Override
	public GenericRepository<People> getRepository() {
		return peopleRepository;
	}

	@Override
	public void alteraStatus(long id) throws Exception {
		if (!peopleRepository.existsById(id))
			throw new Exception("Não existe registro com este Id");
		peopleRepository.alteraStatusById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		People usuario = this.peopleRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("People não encontrado com o e-mail: " + email));
		return usuario;
	}

	@Override
	public void validarSave(People objeto) throws Exception {
		PeopleService.super.validarSave(objeto);
		if (objeto.getPassword().isEmpty())
			throw new Exception("Password não pode ser vazio");
		//Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//if (usuarioLogado.getId() != objeto.getId() && usuarioLogado.getDepartamento().getId() != 1)
		//	throw new Exception("Você só pode alterar os dados referente a sua conta");
		
		
	}


	

}
