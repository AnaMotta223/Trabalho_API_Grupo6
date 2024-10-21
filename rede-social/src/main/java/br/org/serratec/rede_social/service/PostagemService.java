package br.org.serratec.rede_social.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.rede_social.domain.Postagem;
import br.org.serratec.rede_social.repository.PostagemRepository;

@Service
public class PostagemService {

	
	@Autowired
	private PostagemRepository postagemRepository;
	
	
	
	public Postagem buscar (Long id) {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		return postagemOpt.get();
	}
}

