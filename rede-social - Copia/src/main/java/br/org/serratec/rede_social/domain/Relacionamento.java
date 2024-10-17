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
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id_relacionamento")
//	private Long id;
//	
//	@Column(name = "seguidor")
//	private List<Usuario> seguidor;
//	
//	@Column(name = "seguindo")
//	private List<Usuario> seguindo;
//	
	//testar listas de seguidores e seguindo junto com o id da tabela
	
	@Column(name = "data_inicio_seguimento")
	private LocalDate dataInicioSeguimento;
	
	public Relacionamento() { }

//	public Relacionamento(Long id, List<Usuario> seguidor, List<Usuario> seguindo, LocalDate dataInicioSeguimento) {
//		super();
//		this.id = id;
//		this.seguidor = seguidor;
//		this.seguindo = seguindo;
//		this.dataInicioSeguimento = dataInicioSeguimento;
//	}
//	
	
	public Relacionamento(Usuario seguidor, Usuario seguindo, LocalDate dataInicioSeguimento) {
		this.id.setSeguidor(seguidor);
		this.id.setSeguidor(seguidor);
		this.dataInicioSeguimento = dataInicioSeguimento;
	}

	public UsuarioRelacionamentoPK getId() {
		return id;
	}

	public void setId(UsuarioRelacionamentoPK id) {
		this.id = id;
	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public List<Usuario> getSeguidor() {
//		return seguidor;
//	}
//
//	public void setSeguidor(List<Usuario> seguidor) {
//		this.seguidor = seguidor;
//	}
//
//	public List<Usuario> getSeguindo() {
//		return seguindo;
//	}
//
//	public void setSeguindo(List<Usuario> seguindo) {
//		this.seguindo = seguindo;
//	}

	public LocalDate getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setDataInicioSeguimento(LocalDate dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}
	
}
