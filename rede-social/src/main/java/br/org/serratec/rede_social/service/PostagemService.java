package br.org.serratec.rede_social.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.serratec.rede_social.domain.Postagem;
import br.org.serratec.rede_social.dto.PostagemDTO;
import br.org.serratec.rede_social.repository.PostagemRepository;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	
	 public List<PostagemDTO> listar() {
		 List<Postagem> postagens = postagemRepository.findAll(); 
		 List<PostagemDTO> postagensDTO = new ArrayList<>();
		 
		 for(Postagem postagem : postagens) {
			 postagensDTO.add(new PostagemDTO(postagem));
			}
			
			return postagensDTO;
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
	            return null;  
	        }
	        postagem.setId(id);
	        return postagemRepository.save(postagem);
	    }
	 
	 public boolean remover(Long id) {
         if (!postagemRepository.existsById(id)) {
             return false;
         }
         postagemRepository.deleteById(id);
         return true;
     }
	 
//	 public void remover(Long id) {
//	        postagemRepository.deleteById(id);
//	    }
}

