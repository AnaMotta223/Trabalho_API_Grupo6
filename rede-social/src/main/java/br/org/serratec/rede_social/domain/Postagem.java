package br.org.serratec.rede_social.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "postagem")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único da postagem")
	private Long id;
	
	@Column(name = "conteudo", nullable = false, length = 280)
	@Schema(description = "Conteúdo da postagem", requiredMode = RequiredMode.REQUIRED)
	private String conteudo;
	
	@Column(name = "data_hora_criacao", nullable = false)
	private LocalDateTime dataHoraCriacao;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	@Schema(description = "Autor da postagem", requiredMode = RequiredMode.REQUIRED)
	private Usuario autor;
	
	@OneToMany(mappedBy = "postagem")
	private List<Comentario> comentarios = new ArrayList<>();
	

	public Postagem() { 
		this.dataHoraCriacao = LocalDateTime.now();
	}
	
	public Postagem(Long id, String conteudo, Usuario autor) {
		this.id = id;
		this.conteudo = conteudo;
		this.dataHoraCriacao = LocalDateTime.now();
		this.autor = autor;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	
	public LocalDateTime getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postagem other = (Postagem) obj;
		return Objects.equals(id, other.id);
	}
	
}
