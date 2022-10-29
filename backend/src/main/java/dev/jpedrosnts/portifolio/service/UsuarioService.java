package dev.jpedrosnts.portifolio.service;

import dev.jpedrosnts.portifolio.model.Usuario;
import dev.jpedrosnts.portifolio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void atualizarSenha(String emailAdmin, String novaSenha) {
        Optional<Usuario> usuarioOp = repository.findByEmail(emailAdmin);
        if (usuarioOp.isPresent()) {
            usuarioOp.get().setSenha(new BCryptPasswordEncoder().encode(novaSenha));
            repository.save(usuarioOp.get());
        } else {
            Usuario usuario = new Usuario();
            usuario.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
            usuario.setEmail(emailAdmin);
            repository.save(usuario);
        }
    }

    public Usuario findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow();
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }
}
