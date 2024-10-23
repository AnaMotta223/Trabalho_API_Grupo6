package br.org.serratec.rede_social.dto;

import br.org.serratec.rede_social.domain.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

public class RelacionamentoDTO {
	
	@Schema(description = "Id do usuário")
	private Long id;
	
	@Schema(description = "Nome do usuário")
	private String nome;
	
	@Schema(description = "Email do usuário")
	private String email;
	
	public RelacionamentoDTO(Long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public RelacionamentoDTO(Usuario usuario) {
		
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
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
	
}
