package com.wolftech.wolfbills.service;

import com.wolftech.wolfbills.exception.RegraNegocioExcpetion;
import com.wolftech.wolfbills.model.Lancamento;
import com.wolftech.wolfbills.model.Usuario;
import com.wolftech.wolfbills.model.enums.StatusLancamento;
import com.wolftech.wolfbills.repository.LancamentoRepository;
import com.wolftech.wolfbills.repository.LancamentoRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LancamentoServiceTest {

    @SpyBean
    LancamentoService service;

    @MockBean
    LancamentoRepository repository;

//    @Test
//    public void deveSalvarUmLancamento() {
//        //Cenário
//        Lancamento lancamentoASalvar = LancamentoRepositoryTest.criarLancamento();
//        doNothing().when(service).validar(lancamentoASalvar);
//        Lancamento lancamentoSalvo = LancamentoRepositoryTest.criarLancamento();
//        lancamentoSalvo.setId(1L);
//        when(repository.save(lancamentoASalvar)).thenReturn(lancamentoSalvo);
//
//        //Execução
//
//        Lancamento lancamento = service.salvar(lancamentoASalvar);
//
//        //Verificação
//
//        assertThat(lancamento.getId()).isEqualTo(lancamentoSalvo.getId());
//        assertThat(lancamento.getStatusLancamento()).isEqualTo(StatusLancamento.PENDENTE);
//
//    }
//
//    @Test
//    public void naoDeveSalvarQuandoHouverErroDeValidacao() {
//        //Cenário
//        Lancamento lancamentoASalvar = LancamentoRepositoryTest.criarLancamento();
//        doThrow(RegraNegocioExcpetion.class).when(service).validar(lancamentoASalvar);
//
//        //Execução
//        catchThrowableOfType(() -> service.salvar(lancamentoASalvar), RegraNegocioExcpetion.class);
//
//        verify(repository, never()).save(lancamentoASalvar);
//
//    }


    @Test
    public void deveAtualizarUmLancamento() {
        //Cenário
        Lancamento lancamentoSalvo = LancamentoRepositoryTest.criarLancamento();
        lancamentoSalvo.setId(1L);
        lancamentoSalvo.setStatus(StatusLancamento.CANCELADO);
        doNothing().when(service).validar(lancamentoSalvo);
        when(repository.save(lancamentoSalvo)).thenReturn(lancamentoSalvo);

        //Execução

        Lancamento lancamento = service.atualizar(lancamentoSalvo);

        //Verificação

        verify(repository, times(1)).save(lancamentoSalvo);
    }


    @Test
    public void deveLancarErroAoTentarAtualizarUmLancamentoQueNaoFoiSalvo() {
        //Cenário
        Lancamento lancamentoASalvar = LancamentoRepositoryTest.criarLancamento();

        //Execução e verificação
        catchThrowableOfType(() -> service.atualizar(lancamentoASalvar), NullPointerException.class);
        verify(repository, never()).save(lancamentoASalvar);
    }

    @Test
    public void deveDeletarUmLancamento() {

        //Cenário
        Lancamento lancamento = LancamentoRepositoryTest.criarLancamento();
        lancamento.setId(1L);

        //Execução
        service.deletar(lancamento);

        verify(repository).delete(lancamento);

    }

    @Test
    public void deveLancarErroAoTentarDeletarUmLancamentoQueNaofoiSalvo() {

        //Cenário
        Lancamento lancamento = LancamentoRepositoryTest.criarLancamento();

        //Execução

        catchThrowableOfType(() -> service.deletar(lancamento), NullPointerException.class);
        verify(repository, never()).delete(lancamento);
    }

    @Test
    public void deveFiltrarLancamento() {
        //Cenario
        Lancamento lancamento = LancamentoRepositoryTest.criarLancamento();
        lancamento.setId(1L);
        List<Lancamento> lista = Arrays.asList(lancamento);
        when(repository.findAll(any(Example.class))).thenReturn(lista);

        //execuçoã
        List<Lancamento> resultado = service.buscar(lancamento);

        //Verificações

        Assertions
                .assertThat(resultado)
                .isNotEmpty()
                .hasSize(1)
                .contains(lancamento);
    }

    @Test
    public void deveAtualizarOStatusDoLancamento() {
        //Cenario
        Lancamento lancamento = LancamentoRepositoryTest.criarLancamento();
        lancamento.setId(1L);
        lancamento.setStatus(StatusLancamento.PENDENTE);

        StatusLancamento novoStatus = StatusLancamento.EFETIVADO;
        doReturn(lancamento).when(service).atualizar(lancamento);

        //Execução
        service.atualizarStatus(lancamento, novoStatus);

        //Verificação
        assertThat(lancamento.getStatus()).isEqualTo(novoStatus);
        verify(service).atualizar(lancamento);

    }

    @Test
    public void deveObterUmLancamentoPorId() {
        Long id = 1L;
        Lancamento lancamento = LancamentoRepositoryTest.criarLancamento();
        lancamento.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(lancamento));

        //Execução
        Optional<Lancamento> resultado = service.buscarOptionalPorId(id);

        //Verificação
        assertThat(resultado.isPresent()).isTrue();

    }


    @Test
    public void deveRetornarVazioQuandoNaoLocalizarUmLancamentoPeloId() {
        Long id = 1L;
        Lancamento lancamento = LancamentoRepositoryTest.criarLancamento();

        when(repository.findById(id)).thenReturn(Optional.empty());

        //Execução
        Optional<Lancamento> resultado = service.buscarOptionalPorId(id);

        //Verificação
        assertThat(resultado.isPresent()).isFalse();

    }

    @Test
    public void deveLancarErrosAoValidarUmLancamento() {
        Lancamento lancamento = new Lancamento();

        Throwable erro = catchThrowable(() -> service.validar(lancamento));
        assertThat(erro).isInstanceOf(RegraNegocioExcpetion.class).hasMessage("Informe uma descrição válida.");

        lancamento.setDescricao("");
        erro = catchThrowable(() -> service.validar(lancamento));
        assertThat(erro).isInstanceOf(RegraNegocioExcpetion.class).hasMessage("Informe uma descrição válida.");

        lancamento.setDescricao("Salário");
        erro = catchThrowable(() -> service.validar(lancamento));
        assertThat(erro).isInstanceOf(RegraNegocioExcpetion.class).hasMessage("Informe um mês válido.");

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        lancamento.setUsuario(usuario);
        erro = catchThrowable(() -> service.validar(lancamento));
        assertThat(erro).isInstanceOf(RegraNegocioExcpetion.class).hasMessage("Informe um valor válido.");

        lancamento.setValor(BigDecimal.ONE);
        erro = catchThrowable(() -> service.validar(lancamento));
        assertThat(erro).isInstanceOf(RegraNegocioExcpetion.class).hasMessage("Informe um tipo de lançamento.");

    }
}
