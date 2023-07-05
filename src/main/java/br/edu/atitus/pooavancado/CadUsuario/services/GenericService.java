package br.edu.atitus.pooavancado.CadUsuario.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.atitus.pooavancado.CadUsuario.Entities.GenericEntity;
import br.edu.atitus.pooavancado.CadUsuario.repositories.GenericRepository;

public interface GenericService<TEntidade extends GenericEntity> {

	GenericRepository<TEntidade> getRepository();
	
	default void validarSave(TEntidade objeto) throws Exception {
		if (objeto.getNome() == null || objeto.getNome().isEmpty())
			throw new Exception("É necessário informar um nome");
	}
	
	default TEntidade save(TEntidade objeto) throws Exception {
		validarSave(objeto);
		return getRepository().save(objeto);
	}

	default Optional<TEntidade> findById(long id) throws Exception{
		if (!getRepository().existsById(id))
			throw new Exception("Não existe registro com este Id");
		return getRepository().findById(id);
	}

	default void deleteById(long id) {
		getRepository().deleteById(id);;
	}

	default Page<TEntidade> findByNome(Pageable pageable, String nome){
		return getRepository().findByNomeContainingIgnoreCase(pageable, nome);
	}
	
}
