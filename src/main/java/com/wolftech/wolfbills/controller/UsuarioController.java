package com.wolftech.wolfbills.controller;

import com.wolftech.wolfbills.dto.UsuarioDTO;
import com.wolftech.wolfbills.exception.RegraNegocioExcpetion;
import com.wolftech.wolfbills.mapper.UsuarioMapper;
import com.wolftech.wolfbills.model.Usuario;
import com.wolftech.wolfbills.service.LancamentoService;
import com.wolftech.wolfbills.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Api
public class UsuarioController {

    private final UsuarioService service;
    private final LancamentoService lancamentoService;
    private final UsuarioMapper mapper;

    @PostMapping
    @ApiOperation("Salva um novo usuário")
    public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {

        try {
            Usuario usuarioSalvo = service.salvar(mapper.toEntity(dto));
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioExcpetion e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/autenticar")
    @ApiOperation("Rota para autenticar o usuário")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
        Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
        return ResponseEntity.ok(usuarioAutenticado);
    }

    @GetMapping("saldo/{id}")
    @ApiOperation("Rota para obter o saldo  do usuário")
    public ResponseEntity obterSaldo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(lancamentoService.obterSaldo(id));
    }

    @GetMapping("/{id}")
    @ApiOperation("Rota para recuperar o usuário pelo id")
    public ResponseEntity recuperarUsuarioPeloId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @ApiOperation("Rota para listar todos os usuários")
    public ResponseEntity listarTudo() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
