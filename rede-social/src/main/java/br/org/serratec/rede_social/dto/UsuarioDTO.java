package br.org.serratec.rede_social.dto;

import java.util.HashSet;
import java.util.Set;

import br.org.serratec.rede_social.domain.Relacionamento;
import br.org.serratec.rede_social.domain.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Classe DTO que representa um usuário com seus detalhes e relacionamentos")
public class UsuarioDTO {

	@Schema(description = "Id do usuário")
	private Long id;
	
	@Schema(description = "Nome do usuário")
	private String nome;
	
	@Schema(description = "Email do usuário")
	private String email;
	
	@Schema(description = "Seguidores do usuário")
	private Set<RelacionamentoDTO> seguidores;
	
	@Schema(description = "Pessoas que seguem o usuário")
	private Set<RelacionamentoDTO> seguindo;
	
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
		
		for(Relacionamento relacionamento : usuario.getSeguindo()) {
			this.seguidores.add(new RelacionamentoDTO(relacionamento.getId().getSeguidor()));
		}
		for(Relacionamento relacionamento : usuario.getSeguidores()) {
			this.seguindo.add(new RelacionamentoDTO(relacionamento.getId().getSeguindo()));
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


	public Set<RelacionamentoDTO> getSeguidores() {
		return seguidores;
	}


	public void setSeguidores(Set<RelacionamentoDTO> seguidores) {
		this.seguidores = seguidores;
	}


	public Set<RelacionamentoDTO> getSeguindo() {
		return seguindo;
	}


	public void setSeguindo(Set<RelacionamentoDTO> seguindo) {
		this.seguindo = seguindo;
	}

}
