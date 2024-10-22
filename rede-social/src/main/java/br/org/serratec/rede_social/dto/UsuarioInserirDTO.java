package br.org.serratec.rede_social.dto;

import java.time.LocalDate;
import java.util.Set;

import br.org.serratec.rede_social.domain.Postagem;
import br.org.serratec.rede_social.domain.Usuario;

public class UsuarioInserirDTO {
	
	private String nome;
	private String sobrenome;
	private String email;
	private LocalDate dataNascimento;
	private String senha;
	private String confirmaSenha;
	private Set<Usuario> seguidores;
	private Set<Usuario> seguindo;
	private Set<Postagem> postagens;
	
	
	
	public String getNome() {
		return nome;
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


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getSobrenome() {
		return sobrenome;
	}


	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Set<Postagem> getPostagens() {
		return postagens;
	}


	public void setPostagens(Set<Postagem> postagens) {
		this.postagens = postagens;
	}
	
}
