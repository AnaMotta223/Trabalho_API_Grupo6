package br.org.serratec.rede_social.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.rede_social.domain.Postagem;
import br.org.serratec.rede_social.domain.Usuario;
import br.org.serratec.rede_social.dto.UsuarioDTO;
import br.org.serratec.rede_social.dto.UsuarioInserirDTO;
import br.org.serratec.rede_social.repository.UsuarioRepository;
import br.org.serratec.rede_social.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(usuarioService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
		Usuario usuario = usuarioService.buscar(id);
		if (usuario != null) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			return ResponseEntity.ok(usuarioDTO);
		}

		return ResponseEntity.notFound().build();
	}
	
	

	@PostMapping
	public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioInserirDTO usuarioInserirDTO) {
		UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);

		// cria uma uri com o id e substitui o id pelo id do usuario ("usuarios/id")
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(usuarioDTO.getId())
				.toUri();

		// retorna o status como created
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> alterar(@PathVariable Long id, @RequestBody UsuarioInserirDTO usuarioInserirDTO) {

		if (!usuarioService.buscar(id).equals("")) {
			UsuarioDTO usuarioDTO = usuarioService.editar(id, usuarioInserirDTO);
			return ResponseEntity.ok(usuarioDTO);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/buscarPostagem")
	public ResponseEntity<Page<Postagem>> buscarAutorPostagem(@RequestParam(defaultValue = "1") Long autorId, Pageable pageable) {
		Page<Postagem> postagens = usuarioRepository.buscarAutorPostagem(autorId, pageable);
		return ResponseEntity.ok(postagens);
	}

}
