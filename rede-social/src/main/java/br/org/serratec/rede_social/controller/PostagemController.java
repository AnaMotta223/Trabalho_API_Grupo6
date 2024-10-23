package br.org.serratec.rede_social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.org.serratec.rede_social.domain.Postagem;
import br.org.serratec.rede_social.dto.PostagemDTO;
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
	
	@Operation(summary = "Lista todas as postagens", description = "A resposta lista as postagens feitas trazendo a hora e data de criação")
			
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Postagens listadas com sucesso"),
			@ApiResponse(responseCode = "204", description = "Conteúdo não localizado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		}
	) 
	
	
	@GetMapping
	public List<PostagemDTO> listar() {
		return postagemService.listar();
	}
	
	
	 @Operation(summary = "Buscar postagem por ID", description = "A resposta traz as postagens realizadas pelo ID selecionado" )
	    @ApiResponses(value = {
	    		@ApiResponse(responseCode = "200", description = "Postagem encontrada"),
				@ApiResponse(responseCode = "204", description = "Postagem não localizada"),
				@ApiResponse(responseCode = "400", description = "Requisição inválida"),
				@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
				@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
	    })
	
	@GetMapping("/{id}")
	public ResponseEntity<PostagemDTO> buscar(@PathVariable Long id) {
		Postagem postagem = postagemService.buscar(id);
		if (null == postagem) {
			return ResponseEntity.notFound().build();
		}
		PostagemDTO postagemDTO = new PostagemDTO(postagem);
		return ResponseEntity.ok(postagemDTO);
	
	}	
	 
	
	 @Operation(summary = "Inserir uma nova postagem", description = "A resposta cria uma nova postagem, trazendo a hora e data de criação")
		
		@ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "Uma ou mais postagens criadas com sucesso"),
				@ApiResponse(responseCode = "400", description = "Requisição inválida"),
				@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
				@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
				@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
				@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			}
		) 
		
	 
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)//retorna 201
	public PostagemDTO inserir(@RequestBody Postagem postagem) {
		postagem = postagemService.inserir(postagem);
		return new PostagemDTO(postagem);
	}
	 
	 
	 @Operation(summary = "Alterar uma  postagem", description = "A resposta altera uma postagem")
		
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Postagem alterada com sucesso"),
				@ApiResponse(responseCode = "204", description = "Postagem não localizada"),
				@ApiResponse(responseCode = "400", description = "Requisição inválida"),
				@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
				@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
				@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			}
		) 
	 
	
	@PutMapping("/{id}")
	public ResponseEntity<PostagemDTO> alterar(@PathVariable Long id, @Valid @RequestBody Postagem postagem) {
	        if (postagemService.buscar(id) == null) {
	            return ResponseEntity.notFound().build();
	        }
	        Postagem postagens = postagemService.alterar(id, postagem);
	        
	        return ResponseEntity.ok(new PostagemDTO(postagens));
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
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			}
		) 
	 
//	 @DeleteMapping("/{id}")
//	    public ResponseEntity<Void> remover(@PathVariable Long id) {
//	        if (postagemService.buscar(id) == null) {
//	            return ResponseEntity.notFound().build();
//	        }
//	        postagemService.remover(id);
//	        return ResponseEntity.noContent().build();
//	    }	 
	 
	 
	 
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> remover(@PathVariable Long id) {
	        postagemService.remover(id);
	        return ResponseEntity.noContent().build();
	    }	 
	
}
