package com.wolftech.wolfbills.service;

import com.wolftech.wolfbills.dto.LancamentoCadastroDTO;
import com.wolftech.wolfbills.exception.RegraNegocioExcpetion;
import com.wolftech.wolfbills.mapper.LancamentoCadastroMapper;
import com.wolftech.wolfbills.model.Lancamento;
import com.wolftech.wolfbills.model.enums.StatusLancamento;
import com.wolftech.wolfbills.model.enums.TipoLancamento;
import com.wolftech.wolfbills.repository.LancamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LancamentoService {


    private final LancamentoRepository repository;
    private final LancamentoCadastroMapper cadastroMapper;
    private final UsuarioService usuarioService;

    @Transactional
    public Lancamento salvar(LancamentoCadastroDTO dto) {
        Lancamento lancamento = cadastroMapper.toEntity(dto);
        lancamento.setUsuario(usuarioService.buscarPorId(dto.getIdUsuario()));
        validar(lancamento);
        lancamento.setDataCadastro(LocalDate.now());
        lancamento.setStatus(StatusLancamento.PENDENTE);
        return repository.save(lancamento);
    }

    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        validar(lancamento);
        return repository.save(lancamento);
    }

    @Transactional
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        repository.delete(lancamento);
    }

    @Transactional(readOnly = true)
    public List<Lancamento> buscar(Lancamento lancamentoFilter) {
        Example example = Example.of(lancamentoFilter, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        );

        return repository.findAll(example);
    }

    public void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento) {
        lancamento.setStatus(statusLancamento);
        atualizar(lancamento);
    }

    public void validar(Lancamento lancamento) {
        if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
            throw new RegraNegocioExcpetion("Informe uma descrição válida.");
        }


        if (lancamento.getUsuario() == null) {
            throw new RegraNegocioExcpetion("Informe um usuário válido.");
        }

        if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
            throw new RegraNegocioExcpetion("Informe um valor válido.");
        }

        if (lancamento.getTipo() == null) {
            throw new RegraNegocioExcpetion("Informe um tipo de lançamento.");
        }
    }

    @Transactional(readOnly = true)
    public BigDecimal obterSaldo(Long id) {
        BigDecimal receitas = repository.obterSaldoPorTipoLancamentoEUsuarioEStatus(id, TipoLancamento.RECEITA, StatusLancamento.EFETIVADO);
        BigDecimal despesas = repository.obterSaldoPorTipoLancamentoEUsuarioEStatus(id, TipoLancamento.DESPESA, StatusLancamento.EFETIVADO);
        if (receitas == null) {
            receitas = BigDecimal.ZERO;
        }

        if (despesas == null) {
            despesas = BigDecimal.ZERO;
        }
        return receitas.subtract(despesas);
    }

    public Optional<Lancamento> buscarOptionalPorId(Long id) {
        return repository.findById(id);
    }

    public Lancamento buscarPeloId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegraNegocioExcpetion("Não encontrado"));
    }
}
