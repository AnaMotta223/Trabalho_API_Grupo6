package br.org.serratec.rede_social.dto;

import java.util.HashSet;
import java.util.Set;

import br.org.serratec.rede_social.domain.Relacionamento;
import br.org.serratec.rede_social.domain.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private Set<Usuario> seguidores;
	private Set<Usuario> seguindo;
	
	public UsuarioDTO() {
	}
		

	public UsuarioDTO(Long id, String nome, String email) {

		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public UsuarioDTO(Usuario usuario) {

		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.seguidores = new HashSet<>();
		this.seguindo = new HashSet<>();
		
		for(Relacionamento relacionamento : usuario.getSeguidores()) {
			this.seguidores.add(relacionamento.getId().getSeguidor());
		}
		for(Relacionamento relacionamento : usuario.getSeguindo()) {
			this.seguindo.add(relacionamento.getId().getSeguindo());
		}
		
	}
		
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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


	public Set<Usuario> getSeguidores() {
		return seguidores;
	}


	public void setSeguidores(Set<Usuario> seguidores) {
		this.seguidores = seguidores;
	}


	public Set<Usuario> getSeguindo() {
		return seguindo;
	}


	public void setSeguindo(Set<Usuario> seguindo) {
		this.seguindo = seguindo;
	}

}
