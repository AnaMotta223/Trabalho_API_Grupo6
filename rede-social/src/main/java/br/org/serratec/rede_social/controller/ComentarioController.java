package br.org.serratec.rede_social.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import br.org.serratec.rede_social.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
@Tag(name = "Comentarios", description = "Operações relacionadas aos comentários")
public class ComentarioController {

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private ComentarioService comentarioService;

	@Operation(summary = "Lista todos os comentarios", description = "A resposta lista os comentários feitos trazendo a hora e data de criação")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Comentários listados com sucesso"),
			@ApiResponse(responseCode = "204", description = "Conteúdo não localizado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@GetMapping
	public List<Comentario> listar() {
		return comentarioService.listar();
	}

	@Operation(summary = "Buscar comnetários por ID", description = "A resposta traz os comentários realizados pelo ID selecionado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Comentário encontrado"),
			@ApiResponse(responseCode = "204", description = "Comentário não encontrado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@GetMapping("/{id}")
	public ResponseEntity<Comentario> pesquisar(@PathVariable Long id) {
		Optional<Comentario> comentarioOpt = comentarioService.buscarPorId(id);
		if (comentarioOpt.isPresent()) {
			return ResponseEntity.ok(comentarioOpt.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Inserir um novo comentários", description = "A resposta cria um nov comentário, trazendo a hora e data de criação")

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Um ou mais comentários criados com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Comentario inserir(@Valid @RequestBody Comentario comentario) {
		return comentarioService.inserir(comentario);
	}

	@Operation(summary = "Alterar um  comentário", description = "A resposta altera um comentário")

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Comentário alterado com sucesso"),
			@ApiResponse(responseCode = "204", description = "Comentário não localizada"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@PutMapping("/{id}")
	public ResponseEntity<Comentario> atualizar(@PathVariable Long id, @Valid @RequestBody Comentario comentario) {
		Comentario comentarioAtualizado = comentarioService.atualizar(id, comentario);
		if (comentarioAtualizado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(comentarioAtualizado);
	}

	@Operation(summary = "Deletar um comnetário", description = "A resposta deleta um comnetário")

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Comnetário removido com sucesso"),
			@ApiResponse(responseCode = "204", description = "Comnetário deletado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!comentarioService.remover(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/pagina")
    public ResponseEntity<Page<Comentario>> listarPaginado(@PageableDefault(sort = "id",
            direction = Sort.Direction.DESC, page = 0, size = 3) Pageable pageable){
        Page<Comentario> comentarios = comentarioRepository.findAll(pageable);
        return ResponseEntity.ok(comentarios);
    }
	
}

