package br.org.serratec.rede_social.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.rede_social.domain.Usuario;
import br.org.serratec.rede_social.dto.UsuarioDTO;
import br.org.serratec.rede_social.dto.UsuarioInserirDTO;
import br.org.serratec.rede_social.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar(){
		return ResponseEntity.ok(usuarioService.listarTodos());
	}
//	get antigo:
//	@GetMapping("/{id}")
//	public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id){
//		Optional<Usuario> usuarioOpt = usuarioService.buscar(id);
//		
//		if(usuarioOpt.isPresent()) {
//			UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioOpt.get());
//			return ResponseEntity.ok(usuarioDTO);
//		}
//		
//		return ResponseEntity.notFound().build();
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id){
		Usuario usuario = usuarioService.buscar(id);
		if(usuario != null) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			return ResponseEntity.ok(usuarioDTO);
		}
		
		return ResponseEntity.notFound().build();
	}
	   
	@PostMapping
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioInserirDTO usuarioInserirDTO){
		UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);
		
		//cria uma uri com o id e substitui o id pelo id do usuario ("usuarios/id")
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
				.buildAndExpand(usuarioDTO.getId()).toUri();
		
		//retorna o status como created
		return ResponseEntity.created(uri).body(usuarioDTO);
	}
	
	//retornar o not found

//	    @PutMapping("/{id}")
//	    public ResponseEntity<Usuario> alterar(@PathVariable Long id,@RequestBody UsuarioInserirDTO usuarioInserirDTO){
//	        var response = usuarioService.editar(id,usuarioInserirDTO);
//	        return ResponseEntity.ok(usuarioDTO);
//	    }
	
	 @PutMapping("/{id}")
	 public ResponseEntity<UsuarioDTO> alterar(@PathVariable Long id,@RequestBody UsuarioInserirDTO usuarioInserirDTO){ 
		 
		 if(!usuarioService.buscar(id).equals("")) {
			 UsuarioDTO usuarioDTO =  usuarioService.editar(id,usuarioInserirDTO);
			 return ResponseEntity.ok(usuarioDTO);
		 }
		 
	     return ResponseEntity.notFound().build();
	 }
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}