package com.wolftech.wolfbills.model;

import com.wolftech.wolfbills.model.enums.StatusLancamento;
import com.wolftech.wolfbills.model.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tbl_lancamento")
public class Lancamento {

    @Column(name = "cod_lancamento")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;


    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusLancamento status;

    @Column(name = "data_cadastro")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataCadastro;

    @Column(name = "data_vencimento")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataVencimento;

    @ManyToOne
    @JoinColumn(name = "cod_usuario")
    private Usuario usuario;
}
