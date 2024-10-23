package br.org.serratec.rede_social.domain;

import java.io.Serializable;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
@Schema(description = "Chave composta que armazena as referências ao usuário seguidor e ao usuário seguido")
public class UsuarioRelacionamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_usuario_seguidor")
	@Schema(description = "ID do usuário que está seguindo outro usuário")
	// follower
	private Usuario seguidor;

	@ManyToOne
	@JoinColumn(name = "id_usuario_seguindo")
	@Schema(description = "ID do usuário que está sendo seguido")
	// following
	private Usuario seguindo;

	public Usuario getSeguidor() {
		return seguidor;
	}

	public void setSeguidor(Usuario seguidor) {
		this.seguidor = seguidor;
	}

	public Usuario getSeguindo() {
		return seguindo;
	}

	public void setSeguindo(Usuario seguido) {
		this.seguindo = seguido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(seguindo, seguidor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRelacionamentoPK other = (UsuarioRelacionamentoPK) obj;
		return Objects.equals(seguindo, other.seguindo) && Objects.equals(seguidor, other.seguidor);
	}

}
