package com.wolftech.wolfbills.dto;

import com.wolftech.wolfbills.model.enums.StatusLancamento;
import com.wolftech.wolfbills.model.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoDTO {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private TipoLancamento tipo;
    private StatusLancamento status;
    private LocalDate dataCadastro;
    private LocalDate dataVencimento;
    private UsuarioDTO usuario;

}
