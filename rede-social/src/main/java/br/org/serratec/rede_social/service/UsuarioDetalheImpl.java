package br.org.serratec.rede_social.service;

import br.org.serratec.rede_social.domain.Usuario;
import br.org.serratec.rede_social.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetalheImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usurio = usuarioRepository.findByEmail(username);
        if (usurio == null) {
            throw new RuntimeException();
        }
        return usurio;//como ele enxerga o usuario autenticado
    }
}