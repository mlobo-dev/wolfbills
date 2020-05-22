package com.wolftech.wolfbills.repository;

import com.wolftech.wolfbills.model.Lancamento;
import com.wolftech.wolfbills.model.enums.StatusLancamento;
import com.wolftech.wolfbills.model.enums.TipoLancamento;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")

public class LancamentoRepositoryTest {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void deveSalvarUmLancamento() {
        Lancamento lancamento = criarLancamento();
        lancamento = repository.save(lancamento);
        assertThat(lancamento.getId()).isNotNull();

    }

    @Test
    public void deveDeletarUmLancamento() {
        Lancamento lancamento = criarLancamentoEPersistir();
        lancamento = entityManager.find(Lancamento.class, lancamento.getId());
        repository.delete(lancamento);

        Lancamento lancamentoApagado = entityManager.find(Lancamento.class, lancamento.getId());
        assertThat(lancamentoApagado).isNull();

    }

    @Test
    public void deveAtualizarUmLancamento() {
        Lancamento lancamento = criarLancamentoEPersistir();
        lancamento.setAno(2018);
        lancamento.setDescricao("teste");
        lancamento.setStatusLancamento(StatusLancamento.CANCELADO);
        repository.save(lancamento);
        Lancamento lancamentoAtualizado = entityManager.find(Lancamento.class, lancamento.getId());

        assertThat(lancamentoAtualizado.getAno()).isEqualTo(2018);
        assertThat(lancamentoAtualizado.getDescricao()).isEqualTo("teste");
        assertThat(lancamentoAtualizado.getStatusLancamento()).isEqualTo(StatusLancamento.CANCELADO);

    }

    @Test
    public void deveBuscarUmLancamentoPeloId() {
        Lancamento lancamento = criarLancamentoEPersistir();
        Optional<Lancamento> lancamentoEncontrado = repository.findById(lancamento.getId());
        assertThat(lancamentoEncontrado.isPresent()).isTrue();
    }


    public static Lancamento criarLancamento() {
        Lancamento lancamento = Lancamento.builder()
                .ano(2019)
                .mes(1)
                .descricao("Lancamento qualquer")
                .valor(BigDecimal.TEN)
                .tipoLancamento(TipoLancamento.DESPESA)
                .statusLancamento(StatusLancamento.PENDENTE)
                .dataCadastro(LocalDate.now())
                .build();
        return lancamento;
    }


    private Lancamento criarLancamentoEPersistir() {
        Lancamento lancamento = criarLancamento();
        return entityManager.persist(lancamento);
    }

}
