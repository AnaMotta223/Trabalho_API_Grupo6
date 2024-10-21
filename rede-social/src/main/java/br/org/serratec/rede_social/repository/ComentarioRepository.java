package br.org.serratec.rede_social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.rede_social.domain.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	
}
