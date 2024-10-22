package br.org.serratec.rede_social.controller;

import java.util.List;

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

import br.org.serratec.rede_social.domain.Postagem;
import br.org.serratec.rede_social.repository.PostagemRepository;
import br.org.serratec.rede_social.service.PostagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
@Tag(name = "Postagem", description = "Operações relacionadas as postagens")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;

	@Autowired
	private PostagemRepository postagemRepository;

	@Operation(summary = "Lista todas as postagens", description = "A resposta lista as postagens feitas trazendo a hora e data de criação")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Postagens listadas com sucesso"),
			@ApiResponse(responseCode = "204", description = "Conteúdo não localizado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@GetMapping
	public ResponseEntity<List<Postagem>> listar() {// metodo para buscar todas as postagens
		return ResponseEntity.ok(postagemRepository.findAll());// retorna todas as postagens
	}

	@Operation(summary = "Buscar postagem por ID", description = "A resposta traz as postagens realizadas pelo ID selecionado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Postagem encontrada"),
			@ApiResponse(responseCode = "204", description = "Postagem não localizada"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscar(@PathVariable Long id) {// metodo para listar por id
		Postagem postagem = postagemService.buscar(id);// busca pelo id
		if (null == postagem) {// verifica se a postagem existe
			return ResponseEntity.notFound().build();// caso nao exista retorna 404
		}
		return ResponseEntity.ok(postagem);// caso exista retorna 200

	}

	@Operation(summary = "Inserir uma nova postagem", description = "A resposta cria uma nova postagem, trazendo a hora e data de criação")

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Uma ou mais postagens criadas com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // retorna 201
	public Postagem inserir(@RequestBody Postagem postagem) {// metodo para inserir
		postagem = postagemRepository.save(postagem);// salva a postagem
		return postagem;// retorna a postagem
	}

	@Operation(summary = "Alterar uma  postagem", description = "A resposta altera uma postagem")

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Postagem alterada com sucesso"),
			@ApiResponse(responseCode = "204", description = "Postagem não localizada"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@PutMapping("/{id}")
	public ResponseEntity<Postagem> alterar(@PathVariable Long id, @Valid @RequestBody Postagem postagem) {
		if (!postagemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		postagem.setId(id);
		postagem = postagemRepository.save(postagem);
		return ResponseEntity.ok(postagem);
	}

	@Operation(summary = "Deletar uma  postagem", description = "A resposta deleta uma postagem")

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Postagem removida com sucesso"),
			@ApiResponse(responseCode = "204", description = "Postagem deletada"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!postagemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		postagemRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
