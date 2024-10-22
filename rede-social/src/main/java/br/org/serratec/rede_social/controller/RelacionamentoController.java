package br.org.serratec.rede_social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.rede_social.service.RelacionamentoService;

@RestController
@RequestMapping("/relacionamentos")
public class RelacionamentoController {
	
	@Autowired
	private RelacionamentoService relacionamentoService;
	
	@PostMapping("/{usuarioId}/seguindo/{seguindoId}")
	public ResponseEntity<String> seguirUsuario(@PathVariable Long usuarioId, @PathVariable Long seguindoId) {
		relacionamentoService.criarRelacionamento(usuarioId, seguindoId);
		return ResponseEntity.ok("Usuario " + usuarioId + " agora está seguindo o usuario " + seguindoId);
		
	}
	
	@DeleteMapping("/{usuarioId}/seguindo/{seguindoId}")
	public ResponseEntity<String> deixarDeSeguir(@PathVariable Long usuarioId, @PathVariable Long seguindoId) {
		relacionamentoService.deletarRelacionamento(usuarioId, seguindoId);
		return ResponseEntity.ok("Usuario " + usuarioId + " deixou de seguir o usuário " + seguindoId);
	}
	
}
