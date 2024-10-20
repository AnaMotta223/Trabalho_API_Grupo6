package br.org.serratec.rede_social.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.rede_social.domain.Postagem;
import br.org.serratec.rede_social.domain.Relacionamento;
import br.org.serratec.rede_social.domain.Usuario;
import br.org.serratec.rede_social.dto.UsuarioDTO;
import br.org.serratec.rede_social.dto.UsuarioInserirDTO;
import br.org.serratec.rede_social.exception.EmailException;
import br.org.serratec.rede_social.exception.SenhaException;
import br.org.serratec.rede_social.repository.PostagemRepository;
import br.org.serratec.rede_social.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PostagemRepository postagemRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public List<UsuarioDTO> findAll(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		
		for(Usuario usuario : usuarios) {
			usuariosDTO.add(new UsuarioDTO(usuario));
		}
		
		return usuariosDTO;
	}
	
	public Usuario buscar(Long id){
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		return usuarioOpt.get();
		}
	
	@Transactional
	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException, SenhaException{
		
		if(usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) !=null) {
			throw new EmailException("Email já existente!");
		}
		
		if(!usuarioInserirDTO.getSenha().equals(usuarioInserirDTO.getConfirmaSenha())) {
			throw new SenhaException("As senhas não coincidem");
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setSobrenome(usuarioInserirDTO.getSobrenome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setDataNascimento(usuarioInserirDTO.getDataNascimento());
		//colocar a senha criptografada
		usuario.setSenha(encoder.encode(usuarioInserirDTO.getSenha()));
		
		Set<Relacionamento> seguidores = new HashSet<>();
		Set<Relacionamento> seguindo = new HashSet<>();
//		Set<Postagem> postagens = new HashSet<>();
		
		for (Usuario usuarioSeguindo : usuarioInserirDTO.getSeguindo()) {
			usuarioSeguindo = buscar(usuarioSeguindo.getId());
			Relacionamento relacionamento = new Relacionamento(usuario, usuarioSeguindo, LocalDate.now());
			seguindo.add(relacionamento);
		}
		
		for (Usuario usuarioSeguidor : usuarioInserirDTO.getSeguidores()) {
			usuarioSeguidor = buscar(usuarioSeguidor.getId());
			Relacionamento relacionamento = new Relacionamento(usuarioSeguidor, usuario, LocalDate.now());
			seguidores.add(relacionamento);
		}
		
		//fazer o service de postagem com a busca com optional
//		for (Postagem postagemUsuario : usuarioInserirDTO.getPostagens()) {
//			postagemUsuario = postagemRepository.findById(postagemUsuario.getId());
//			Postagem postagem = new Postagem(postagemUsuario);
//			postagens.add(postagem);
//		}
		
		usuario.setSeguidores(seguidores);
		usuario.setSeguindo(seguindo);
		//usuario.setPostagens(postagens);
		
		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}
	
}
