package br.org.serratec.rede_social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.org.serratec.rede_social.domain.Relacionamento;
import br.org.serratec.rede_social.domain.UsuarioRelacionamentoPK;


@Repository
public interface RelacionamentoRepository extends JpaRepository<Relacionamento, UsuarioRelacionamentoPK> {

}
