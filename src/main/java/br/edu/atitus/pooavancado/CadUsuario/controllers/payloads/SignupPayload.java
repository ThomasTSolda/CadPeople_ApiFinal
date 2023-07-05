package br.edu.atitus.pooavancado.CadUsuario.controllers.payloads;

import br.edu.atitus.pooavancado.CadUsuario.Entities.Setor;

public class SignupPayload {
	
	private String nome;
	private String email;
	private Setor departamento;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Setor getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Setor departamento) {
		this.departamento = departamento;
	}
	
	

}
