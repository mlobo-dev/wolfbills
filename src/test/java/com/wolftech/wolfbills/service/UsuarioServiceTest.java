package com.wolftech.wolfbills.service;

import com.wolftech.wolfbills.exception.ErroAutenticacaoExcpetion;
import com.wolftech.wolfbills.exception.RegraNegocioExcpetion;
import com.wolftech.wolfbills.model.Usuario;
import com.wolftech.wolfbills.repository.UsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @SpyBean
    private UsuarioService service;

    @MockBean
    private UsuarioRepository repository;


    @Test
    public void deveAutenticarUmUsuarioComSucesso() {
        //Cenário
        String email = "email@email.com";
        String senha = "senha";
        Usuario usuario = Usuario.builder().email(email).senha(senha).id(1L).build();
        when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

        //Ação
        Usuario result = service.autenticar(email, senha);

        //Verificação
        assertThat(result).isNotNull();
    }

    @Test
    public void deveLancarErroQuandoNaoEncontrarUsuarioComOEmailCadastrado() {
        //Cenário
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        Throwable exception = catchThrowable(() -> service.autenticar("email@email", "23213"));
        assertThat(exception).isInstanceOf(ErroAutenticacaoExcpetion.class).hasMessage("Usuário não encontrado, email: email@email");
    }

    @Test
    public void deveLancarErroQuandoSenhaNaoConferir() {
        //Cenário
        //Cenário
        String email = "email@email.com";
        String senha = "senha";
        Usuario usuario = Usuario.builder().email(email).senha(senha).id(1L).build();
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(usuario));
        Throwable exception = catchThrowable(() -> service.autenticar("email@email", "23213"));
        assertThat(exception).isInstanceOf(ErroAutenticacaoExcpetion.class).hasMessage("Senha Inválida.");
    }

    @Test
    public void deveValidarEmail() {
        //Cenário
        Mockito.when(repository.existsByEmail(anyString())).thenReturn(false);
        service.validarEmail("email@rmail.com");
    }

    @Test
    public void deveLancarErroAoValidarEmailQuandoExistirUsuarioCadastrado() {
        // Cenário
        Mockito.when(repository.existsByEmail(anyString())).thenReturn(true);
        //Ação

        Throwable exception = catchThrowable(() -> service.validarEmail("email@mail.com"));
        assertThat(exception).isInstanceOf(RegraNegocioExcpetion.class).hasMessage("Já existe um usuário cadastrado com esse email");
    }

    @Test
    public void deveSalvarUsuario() {
        //Cenário
        doNothing().when(service).validarEmail(anyString());
        Usuario usuario = criarUsuario();
        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        //Ação
        Usuario result = service.salvar(new Usuario());

        //Verificação
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNome()).isEqualTo("Usuario");
        assertThat(result.getEmail()).isEqualTo("email@mail.com");
        assertThat(result.getSenha()).isEqualTo("123");

    }

    @Test(expected = RegraNegocioExcpetion.class)
    public void naoDeveSalvarUmUsuarioComEmailJaCadastrado() {
        //Cenário
        String email = "email@email.com";
        Usuario usuario = Usuario.builder().email(email).build();
        doThrow(RegraNegocioExcpetion.class).when(service).validarEmail(email);

        //Ação
        service.salvar(usuario);

        //Verificação
        verify(repository, never()).save(usuario);


    }

    public static Usuario criarUsuario() {
        Usuario usuario = Usuario.builder().nome("Usuario").email("email@mail.com").senha("123").id(1L).build();
        return usuario;
    }
}
