package br.org.serratec.rede_social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.rede_social.domain.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
