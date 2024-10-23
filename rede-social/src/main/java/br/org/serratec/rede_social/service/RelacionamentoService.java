package br.org.serratec.rede_social.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.rede_social.domain.Relacionamento;
import br.org.serratec.rede_social.domain.Usuario;
import br.org.serratec.rede_social.repository.RelacionamentoRepository;
import jakarta.transaction.Transactional;

@Service
public class RelacionamentoService {

	@Autowired
	private RelacionamentoRepository relacionamentoRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Transactional
	public Relacionamento criarRelacionamento(Long usuarioId, Long seguindoId) {
		Usuario seguidor = usuarioService.buscar(usuarioId); // buscando quem TA SEGUINDO
		Usuario seguindo = usuarioService.buscar(seguindoId); // buscando quem TA SENDO SEGUIDO
		
		if (relacionamentoRepository.findByIdSeguidorAndIdSeguindo(seguidor, seguindo).isPresent()) {
			throw new RuntimeException("Esse relacionamento já existe");

		}

		Relacionamento relacionamento = new Relacionamento();
		relacionamento.getId().setSeguidor(seguidor);
		relacionamento.getId().setSeguindo(seguindo);
		relacionamento.setDataInicioSeguimento(LocalDate.now());

		return relacionamentoRepository.save(relacionamento);
	}

	@Transactional
	public void deletarRelacionamento(Long usuarioId, Long seguindoId) {
		Usuario seguidor = usuarioService.buscar(usuarioId);
		Usuario seguindo = usuarioService.buscar(seguindoId);

		if (relacionamentoRepository.findByIdSeguidorAndIdSeguindo(seguidor, seguindo).isEmpty()) {
			throw new RuntimeException("Esse relacionamento não existe");
			
		}		
	
		Optional<Relacionamento> relacionamento = relacionamentoRepository.findByIdSeguidorAndIdSeguindo(seguidor, seguindo); 

		relacionamentoRepository.delete(relacionamento.get());
	}

}