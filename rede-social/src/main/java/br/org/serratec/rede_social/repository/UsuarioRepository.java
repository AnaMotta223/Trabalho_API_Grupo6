package br.org.serratec.rede_social.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.serratec.rede_social.domain.Postagem;
import br.org.serratec.rede_social.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByEmail(String email);
	
	@Query(value = "SELECT p FROM Postagem p WHERE p.autor.id = :autorId")
	Page<Postagem> buscarAutorPostagem(Long autorId, Pageable pageable);
	

}
