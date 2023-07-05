package br.edu.atitus.pooavancado.CadUsuario.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "setor")
public class Setor extends GenericEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = true, length = 200)
	private String setor;
	
	@Column(nullable = true)
	private Integer ramal;
	
	@Column(nullable = true, length = 150)
	private String email;

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public Integer getRamal() {
		return ramal;
	}

	public void setRamal(Integer ramal) {
		this.ramal = ramal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
