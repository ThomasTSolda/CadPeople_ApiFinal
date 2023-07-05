package br.edu.atitus.pooavancado.CadUsuario.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import br.edu.atitus.pooavancado.CadUsuario.Entities.GenericEntity;
import br.edu.atitus.pooavancado.CadUsuario.services.GenericService;

public abstract class GenericController<TEntidade extends GenericEntity> {
	
	abstract GenericService<TEntidade> getService();

	private ResponseEntity<Object> salvar(TEntidade entidade) {
		try {
			getService().save(entidade);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(entidade);
	}
	
	@PostMapping
	public ResponseEntity<Object> postPeople(@RequestBody TEntidade entidade) {
		return salvar(entidade);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putPeople(@PathVariable long id, @RequestBody TEntidade entidade) {
		entidade.setId(id);
		return salvar(entidade);
	}

	@GetMapping()
	public ResponseEntity<Object> getEntidades(@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam String nome) throws Exception {
		Page<TEntidade> entidades = getService().findByNome(pageable, nome);
		for (TEntidade tEntidade : entidades) {
			long id = tEntidade.getId();
			tEntidade.add(linkTo(methodOn(GenericController.class).getEntidadesById(id)).withSelfRel());
			tEntidade.add(linkTo(methodOn(GenericController.class).putPeople(id, tEntidade))
					.withRel("PUT - Alterar"));
			tEntidade.add(linkTo (methodOn(GenericController.class).deletePeople(id)).withRel("DELETE - Remover"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(entidades);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getEntidadesById(@PathVariable long id) throws Exception {
		Optional<TEntidade> entidade = getService().findById(id);
		if (entidade.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TEntidade não encontrado com o Id " + id);
		else {
			
		      entidade.get().add(linkTo(methodOn(GenericController.class).getEntidadesById(id)).withSelfRel());
		      entidade.get().add(linkTo(methodOn(GenericController.class).putPeople(id, entidade.get())).withRel("PUT - Alterar"));
		      entidade.get().add(linkTo(methodOn(GenericController.class).deletePeople(id)).withRel("DELETE - Remover"));

		      // Adiciona o link para retornar à lista de usuários
		      entidade.get().add(linkTo(GenericController.class).withRel("Lista de Registros"));

			return ResponseEntity.status(HttpStatus.OK).body(entidade);
		}
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePeople(@PathVariable long id) {
		getService().deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("People with Id " + id + " success deleted!");
	}

}
