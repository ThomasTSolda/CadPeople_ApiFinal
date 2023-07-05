package br.edu.atitus.pooavancado.CadUsuario.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.edu.atitus.pooavancado.CadUsuario.Entities.People;

public interface PeopleService extends GenericService<People>, UserDetailsService{
	
	void alteraStatus(long id) throws Exception;

}
