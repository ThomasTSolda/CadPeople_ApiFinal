package br.edu.atitus.pooavancado.CadUsuario.Entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "people")
public class People extends GenericEntity implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = true, length = 200)
	private String email;
	
	@Column(nullable = false)
	private boolean status;
	
	@ManyToOne
	@JoinColumn(nullable = true, name ="id_departamento")
	private Setor departamento;
	
	@Column(nullable = true)
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Setor getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Setor departamento) {
		this.departamento = departamento;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}


}
