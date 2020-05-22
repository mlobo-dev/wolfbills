package com.wolftech.wolfbills.repository;

import com.wolftech.wolfbills.model.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @org.junit.Test
    public void deveVerificarAExistenciaDeUmEmail() {
        // Cenário
        Usuario usuario = Usuario.builder().nome("Usuario").email("email@mail.com").senha("123").build();
        entityManager.persist(usuario);
        //Ação
        boolean result = repository.existsByEmail("email@mail.com");

        //Verificação
        Assertions.assertThat(result).isTrue();

    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
        //Ação

        boolean result = repository.existsByEmail("email@mail.com");

        //Verificação
        Assertions.assertThat(result).isFalse();

    }

    @Test
    public void devePersistirUmUsuarioNaBaseDeDados() {
        // Cenário
        Usuario usuario = criarUsuario();

        //Ação
        Usuario result = repository.save(usuario);

        Assertions.assertThat(result.getId()).isNotNull();

    }

    @Test
    public void deveBuscarUmUsuarioPorEmail() {
        //Cenário
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);

        //verificação
        Optional<Usuario> result = repository.findByEmail("email@mail.com");

        Assertions.assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void deveRetornarVazioAoBuscarUmUsuarioPorEmailQuandoNaoExisteNaBase() {
        //verificação
        Optional<Usuario> result = repository.findByEmail("email@mail.com");

        Assertions.assertThat(result.isPresent()).isFalse();
    }

    public static Usuario criarUsuario() {
        Usuario usuario = Usuario.builder().nome("Usuario").email("email@mail.com").senha("123").build();
        return usuario;
    }
}
