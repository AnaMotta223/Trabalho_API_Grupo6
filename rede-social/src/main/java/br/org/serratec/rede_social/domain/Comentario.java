package br.org.serratec.rede_social.domain;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "Representa um comentário feito em uma postagem")
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario")
	@Schema(description = "ID do comentário")
	private Long id;

	@NotBlank(message = "Adicione o texto do cometário!")
	@Size(min = 2, max = 280, message = "O tamanho do comentário deve ter entre {min} a {max} caractere.")
	@Column(name = "texto", nullable = false, length = 280)
	@Schema(description = "Texto do comentário")
	private String texto;
	
	@Column(name = "data_criacao", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Schema(description = "Data de criação do comentário")
	private LocalDate dataCriacao;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_postagem")
	@Schema(description = "A postagem à qual este comentário pertence")
	private Postagem postagem;
	
	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
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
		Comentario other = (Comentario) obj;
		return Objects.equals(id, other.id);
	}
	
}