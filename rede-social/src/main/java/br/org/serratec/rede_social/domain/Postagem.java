package br.org.serratec.rede_social.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
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
import jakarta.validation.Valid;

@Entity
@Table(name = "postagem")
@Schema(description = "Representa uma postagem feita por um usuário")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID da postagem")
	private Long id;

	@NotBlank(message = "Adicione o texto do conteúdo!")
	@Size(max = 280, min = 2, message = "O tamanho da postagem deve ter entre {min} a {max} caractere.")
	@Column(name = "conteudo", nullable = false, length = 280)
	@Schema(description = "Conteúdo da postagem")
	private String conteudo;
	
	@Column(name = "data_hora_criacao", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Schema(description = "Data e hora de criação da postagem")
	private LocalDateTime dataHoraCriacao;

	@Valid
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	@Schema(description = "Autor da postagem")
	private Usuario autor;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "postagem")
	@Schema(description = "Lista de comentários relacionados à postagem")
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