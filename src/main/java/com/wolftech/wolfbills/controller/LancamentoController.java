package com.wolftech.wolfbills.controller;

import com.wolftech.wolfbills.dto.AtualizaStatusDTO;
import com.wolftech.wolfbills.dto.LancamentoCadastroDTO;
import com.wolftech.wolfbills.dto.LancamentoDTO;
import com.wolftech.wolfbills.exception.RegraNegocioExcpetion;
import com.wolftech.wolfbills.mapper.LancamentoMapper;
import com.wolftech.wolfbills.model.Lancamento;
import com.wolftech.wolfbills.model.enums.StatusLancamento;
import com.wolftech.wolfbills.service.LancamentoService;
import com.wolftech.wolfbills.service.UsuarioService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lancamentos")
@RequiredArgsConstructor
@Api
public class LancamentoController {

    private final LancamentoService service;
    private final LancamentoMapper mapper;
    private final UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity buscar(
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "mes", required = false) Integer mes,
            @RequestParam(value = "ano", required = false) Integer ano,
            @RequestParam(value = "usuario", required = true) Long idUsuario) {
        Lancamento filter = new Lancamento();
        filter.setAno(ano);
        filter.setMes(mes);
        filter.setDescricao(descricao);
        filter.setUsuario(usuarioService.buscarPorId(idUsuario));
        return ResponseEntity.ok(service.buscar(filter));

    }


    @PostMapping
    public ResponseEntity salvar(@RequestBody LancamentoCadastroDTO dto) {

            Lancamento lancamentoSalvo = service.salvar(dto);
            return new ResponseEntity(lancamentoSalvo, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody LancamentoDTO dto) {
        return service.buscarOptionalPorId(id).map(entity -> {
            try {
                Lancamento lancamento = mapper.toEntity(dto);
                lancamento.setId(entity.getId());
                lancamento.setUsuario(usuarioService.buscarPorId(dto.getIdUsuario()));
                service.atualizar(lancamento);
                return ResponseEntity.ok(lancamento);
            } catch (RegraNegocioExcpetion e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity("Lancamento não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return service.buscarOptionalPorId(id).map(entity -> {
            service.deletar(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity("Lancamento não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDTO> buscarPeloId(@PathVariable Long id) {
        Lancamento lancamento = service.buscarPeloId(id);
        LancamentoDTO dto = mapper.toDto(lancamento);
        dto.setIdUsuario(lancamento.getUsuario().getId());
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/atualizar-status/{id}")
    public ResponseEntity atualizar(@RequestBody AtualizaStatusDTO dto, @PathVariable Long id) {
        return service.buscarOptionalPorId(id).map(entity -> {
            StatusLancamento statusSelecionado = StatusLancamento.valueOf(dto.getStatus());
            if (statusSelecionado == null) {
                return ResponseEntity.badRequest().body("Não foi possível atualizar o status do lançamento, envie um status válido");
            }

            try {
                entity.setStatusLancamento(statusSelecionado);
                entity = service.atualizar(entity);
                return ResponseEntity.ok(entity);
            } catch (RegraNegocioExcpetion e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }).orElseGet(() -> new ResponseEntity("Lancamento não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
    }


}
