package br.org.serratec.rede_social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.rede_social.service.RelacionamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/relacionamentos")
@Tag(name = "Relacionamentos", description = "Gerenciamento de relacionamentos entre usuários")
public class RelacionamentoController {
	
	@Autowired
	private RelacionamentoService relacionamentoService;
	
	@Operation(summary = "Seguindo um usuário", description = "A resposta permite que um usuário siga outro usuario")

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Usuário seguido com sucesso"),
			@ApiResponse(responseCode = "204", description = "Usuário não localizado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	
	@PutMapping("/{usuarioId}/seguindo/{seguindoId}")
	public ResponseEntity<String> seguirUsuario(
			@Parameter (description = "ID do usuário que deseja seguir outro") 
			@PathVariable Long usuarioId, 
			@Parameter (description = "ID do usuário a ser seguido")
			@PathVariable Long seguindoId) {
		relacionamentoService.criarRelacionamento(usuarioId, seguindoId);
		return ResponseEntity.ok("Usuario " + usuarioId + " agora está seguindo o usuario " + seguindoId);
		
	}
	
	@Operation(summary = "Deixar de seguir um usuário", description = "A resposta permite que um usuário deixe de seguir outro")

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Usuário deixou de seguir outro com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Operação proibida e não pode ser concluída"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	
	@DeleteMapping("/{usuarioId}/seguindo/{seguindoId}")
	public ResponseEntity<String> deixarDeSeguir(
			@Parameter(description = "ID do usuário que deseja deixar de seguir outro usuário")
			@PathVariable Long usuarioId, 
			@Parameter(description = "ID do usuário que não será mais seguido")
			@PathVariable Long seguindoId) {
		relacionamentoService.deletarRelacionamento(usuarioId, seguindoId);
		return ResponseEntity.ok("Usuario " + usuarioId + " deixou de seguir o usuário " + seguindoId);
	}
	
}
