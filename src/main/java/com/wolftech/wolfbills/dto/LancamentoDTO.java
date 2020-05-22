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
    private Integer ano;
    private Integer mes;
    private BigDecimal valor;
    private TipoLancamento tipoLancamento;
    private StatusLancamento statusLancamento;
    private LocalDate dataCadastro;
    private Long idUsuario;

}
