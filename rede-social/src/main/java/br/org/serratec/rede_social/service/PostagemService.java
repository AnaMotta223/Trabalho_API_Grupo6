package br.org.serratec.rede_social.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.serratec.rede_social.domain.Comentario;
import br.org.serratec.rede_social.domain.Postagem;
import br.org.serratec.rede_social.repository.PostagemRepository;
import jakarta.validation.Valid;

@Service
public class PostagemService {

	
	@Autowired
	private PostagemRepository postagemRepository;
	
	
	 public List<Postagem> listar() {
	        return postagemRepository.findAll();
	    }
	
	public Postagem buscar (Long id) {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		return postagemOpt.get();
	}
	
	 public Postagem inserir(@RequestBody Postagem postagem) {
		 postagem = postagemRepository.save(postagem); 
	        return postagem;
	    }

	 	 
	 public Postagem alterar(Long id, Postagem postagem) {
	        if (!postagemRepository.existsById(id)) {
	            return null; // 
	        }
	        postagem.setId(id);
	        return postagemRepository.save(postagem);
	    }
	 
	/* public ResponseEntity<Postagem> alterar(@PathVariable Long id, @Valid @RequestBody Postagem postagem) {
			if (!postagemRepository.existsById(id)) {
				return ResponseEntity.notFound().build();
			}
			postagem.setId(id);
			postagem = postagemRepository.save(postagem);
			return ResponseEntity.ok(postagem);
		}
	 

	/* public ResponseEntity<Void> remover(@PathVariable Long id) {
			if (!postagemRepository.existsById(id)) {
				return ResponseEntity.notFound().build();
			}
			postagemRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}*/
	 public boolean remover(Long id) {
	        if (!postagemRepository.existsById(id)) {
	            return false;
	        }
	        postagemRepository.deleteById(id);
	        return true;
	    }
}

