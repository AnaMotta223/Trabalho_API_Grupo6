package br.org.serratec.rede_social.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "relacionamento")
@Schema(description = "Representa um relacionamento de seguimento entre dois usuários")
public class Relacionamento {
	
	@EmbeddedId
	@Schema(description = "Chave composta que contém o ID do seguidor e o ID do usuário seguido.")
	private UsuarioRelacionamentoPK id = new UsuarioRelacionamentoPK();
	
	@Column(name = "data_inicio_seguimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Schema(description = "Data em que o seguidor começou a seguir o outro usuário.")
	private LocalDate dataInicioSeguimento;
	
	public Relacionamento() { }

	public Relacionamento(Usuario seguidor, Usuario seguindo, LocalDate dataInicioSeguimento) {
		this.id.setSeguidor(seguidor);
		this.id.setSeguidor(seguidor);
		this.dataInicioSeguimento = LocalDate.now();
	}

	public UsuarioRelacionamentoPK getId() {
		return id;
	}

	public void setId(UsuarioRelacionamentoPK id) {
		this.id = id;
	}

	public LocalDate getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setDataInicioSeguimento(LocalDate dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}
	
}
