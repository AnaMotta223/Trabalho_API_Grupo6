package br.org.serratec.rede_social.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.serratec.rede_social.domain.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	@Query(value = "SELECT * FROM comentario WHERE data_criacao = ?1", nativeQuery = true)
	List<Comentario> findComentariosByDataCriacao(LocalDate dataCriacao);
}
