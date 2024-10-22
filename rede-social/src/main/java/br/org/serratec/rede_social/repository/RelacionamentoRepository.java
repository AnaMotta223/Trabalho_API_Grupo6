package br.org.serratec.rede_social.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.rede_social.domain.Relacionamento;
import br.org.serratec.rede_social.domain.Usuario;
import br.org.serratec.rede_social.domain.UsuarioRelacionamentoPK;


@Repository
public interface RelacionamentoRepository extends JpaRepository<Relacionamento, UsuarioRelacionamentoPK> {
	Optional<Relacionamento> findByIdSeguidorAndIdSeguindo(Usuario seguidor, Usuario seguindo);

}
	
