package br.org.serratec.rede_social.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.rede_social.domain.Comentario;
import br.org.serratec.rede_social.repository.ComentarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping
    public List<Comentario> listar() {
        List<Comentario> lista = comentarioRepository.findAll();
        return lista;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> pesquisar(@PathVariable Long id) {
        Optional<Comentario> produtoOpt = comentarioRepository.findById(id);
        if (produtoOpt.isPresent()) {
            Comentario comentario = produtoOpt.get();
            return ResponseEntity.ok(comentario);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comentario inserir(@Valid @RequestBody Comentario comentario) {
    return  comentarioRepository.save(comentario);
    }

    
 
    
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> atualizar(@PathVariable Long id, @Valid @RequestBody Comentario comentario) {
        
        Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
        if (comentarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        comentario.setId(id);
        
      
        comentario = comentarioRepository.save(comentario);
        return ResponseEntity.ok(comentario);
    }
    
    
    
    
    
    
    
    
    
    
/*  
 
	@PutMapping("/{id}")
	public ResponseEntity<Comentario> atualizar(@PathVariable Long id, @Valid @RequestBody Comentario comentario) {
		
		Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
		if (comentarioOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		comentario = comentarioRepository.save(comentario);
		return ResponseEntity.ok(comentario);
	}
  */  
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!comentarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		comentarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
  }

