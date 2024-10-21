package br.org.serratec.rede_social.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "relacionamento")
public class Relacionamento {
	
	@EmbeddedId
	private UsuarioRelacionamentoPK id = new UsuarioRelacionamentoPK();
	
	@Column(name = "data_inicio_seguimento")
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
