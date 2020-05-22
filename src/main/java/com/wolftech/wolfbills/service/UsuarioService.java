package com.wolftech.wolfbills.service;

import com.wolftech.wolfbills.exception.ErroAutenticacaoExcpetion;
import com.wolftech.wolfbills.exception.RegraNegocioExcpetion;
import com.wolftech.wolfbills.model.Usuario;
import com.wolftech.wolfbills.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuario = repository.findByEmail(email);
        if (!usuario.isPresent()) {
            throw new ErroAutenticacaoExcpetion("Usuário não encontrado, email: " + email);
        }

        if (!usuario.get().getSenha().equals(senha)) {
            throw new ErroAutenticacaoExcpetion("Senha Inválida.");
        }
        return usuario.get();
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    public void validarEmail(String email) {
        if (repository.existsByEmail(email)) {
            throw new RegraNegocioExcpetion("Já existe um usuário cadastrado com esse email");
        }
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegraNegocioExcpetion("Objeto não Localizado pelo Id:" + id));
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }


}
