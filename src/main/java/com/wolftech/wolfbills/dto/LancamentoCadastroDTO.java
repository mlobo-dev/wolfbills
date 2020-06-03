package com.wolftech.wolfbills.dto;

import com.wolftech.wolfbills.model.enums.StatusLancamento;
import com.wolftech.wolfbills.model.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoCadastroDTO {

    private Long id;
    @NotNull
    private Long idUsuario;
    @NotEmpty
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private TipoLancamento tipo;

    @NotNull
    private StatusLancamento status;

    private LocalDate dataCadastro;

    @NotNull
    private LocalDate dataVencimento;

}
