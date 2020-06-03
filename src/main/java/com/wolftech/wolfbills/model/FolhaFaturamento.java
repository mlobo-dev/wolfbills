package com.wolftech.wolfbills.model;

import com.wolftech.wolfbills.model.enums.MesEnum;
import com.wolftech.wolfbills.model.enums.StatusFolhaFaturamento;
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
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tbl_folha_faturamento")
public class FolhaFaturamento {

    @Column(name = "cod_folha_faturamento")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mes")
    private MesEnum mes;

    @Column(name = "status")
    private StatusFolhaFaturamento status;

    @ManyToMany
    @JoinTable(name = "tbl_folha_lancamentos",
            joinColumns = @JoinColumn(name = "cod_folha_faturamento"),
            inverseJoinColumns = @JoinColumn(name = "cod_lancamento")
    )
    private Set<Lancamento> lancamentos = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "cod_usuario")
    private Usuario usuario;
}
