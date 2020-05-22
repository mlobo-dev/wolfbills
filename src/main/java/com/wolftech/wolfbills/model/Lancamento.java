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

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusLancamento statusLancamento;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "data_cadastro")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataCadastro;
}
