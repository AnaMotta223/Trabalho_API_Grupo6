package br.org.serratec.rede_social.service;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.rede_social.domain.Comentario;
import br.org.serratec.rede_social.repository.ComentarioRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> listar() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> buscarPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    public Comentario inserir(Comentario comentario) {
        comentario.setDataCriacao(LocalDate.now()); 
        return comentarioRepository.save(comentario);
    }

    public Comentario atualizar(Long id, Comentario comentario) {
        if (!comentarioRepository.existsById(id)) {
            return null; 
        }
        comentario.setId(id);
        comentario.setDataCriacao(LocalDate.now());
        return comentarioRepository.save(comentario);
    }

    public boolean remover(Long id) {
        if (!comentarioRepository.existsById(id)) {
            return false;
        }
        comentarioRepository.deleteById(id);
        return true;
    }
}