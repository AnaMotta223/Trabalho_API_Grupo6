package br.org.serratec.rede_social.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
import br.org.serratec.rede_social.dto.PostagemDTO;
import br.org.serratec.rede_social.dto.UsuarioDTO;
import br.org.serratec.rede_social.dto.UsuarioInserirDTO;
import br.org.serratec.rede_social.repository.UsuarioRepository;
import br.org.serratec.rede_social.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Operações relacionadas aos usuários")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Operation(summary = "Lista todos os usuários", description = "A resposta lista os usuários cadastrados com todos os seus dados(nome,sobrenome, data de nascimento, seguidores e quem segue)")

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Usuários listados com sucesso"),
			@ApiResponse(responseCode = "204", description = "Conteúdo não localizado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(usuarioService.listarTodos());
	}

	@Operation(summary = "Buscar usuário por ID", description = "A resposta traz o usuário pelo ID selecionado")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Usuário encontrado"),
			@ApiResponse(responseCode = "204", description = "Ususário não localizado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
		Usuario usuario = usuarioService.buscar(id);
		if (usuario != null) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			return ResponseEntity.ok(usuarioDTO);
		}

		return ResponseEntity.notFound().build();
	}
	
	@Operation(summary = "Inserir um novo usuário", description = "A resposta cria um novo usuário com todos os seus dados (nome,sobrenome, email, senha e data de nascimento)")

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@PostMapping
	public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioInserirDTO usuarioInserirDTO) {
		UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);

		// cria uma uri com o id e substitui o id pelo id do usuario ("usuarios/id")
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(usuarioDTO.getId())
				.toUri();

		// retorna o status como created
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

	@Operation(summary = "Alterar um usuário", description = "A resposta altera um usuario")

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Usuário alterado com sucesso"),
			@ApiResponse(responseCode = "204", description = "Usuário não localizado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> alterar(@PathVariable Long id, @RequestBody UsuarioInserirDTO usuarioInserirDTO) {

		if (!usuarioService.buscar(id).equals("")) {
			UsuarioDTO usuarioDTO = usuarioService.editar(id, usuarioInserirDTO);
			return ResponseEntity.ok(usuarioDTO);
		}

		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Deletar um  usuário", description = "A resposta deleta um usuário")

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
			@ApiResponse(responseCode = "204", description = "Usuário deletado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@GetMapping("/buscarPostagem")
	public ResponseEntity<Page<PostagemDTO>> buscarAutorPostagem(@RequestParam(defaultValue = "1") Long autorId, Pageable pageable) {
		Page<Postagem> postagens = usuarioRepository.buscarAutorPostagem(autorId, pageable);
		List<PostagemDTO> postagensDTO = postagens.stream().map(PostagemDTO :: new).toList();
		return ResponseEntity.ok(new PageImpl<>(postagensDTO, pageable, postagens.getTotalElements()));
	}

}
