package com.wolftech.wolfbills.mapper;

import com.wolftech.wolfbills.dto.LancamentoDTO;
import com.wolftech.wolfbills.model.Lancamento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-22T13:35:00-0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class LancamentoMapperImpl implements LancamentoMapper {

    @Override
    public LancamentoDTO toDto(Lancamento entity) {
        if ( entity == null ) {
            return null;
        }

        LancamentoDTO lancamentoDTO = new LancamentoDTO();

        lancamentoDTO.setId( entity.getId() );
        lancamentoDTO.setDescricao( entity.getDescricao() );
        lancamentoDTO.setAno( entity.getAno() );
        lancamentoDTO.setMes( entity.getMes() );
        lancamentoDTO.setValor( entity.getValor() );
        lancamentoDTO.setTipoLancamento( entity.getTipoLancamento() );
        lancamentoDTO.setStatusLancamento( entity.getStatusLancamento() );
        lancamentoDTO.setDataCadastro( entity.getDataCadastro() );

        return lancamentoDTO;
    }

    @Override
    public Lancamento toEntity(LancamentoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Lancamento lancamento = new Lancamento();

        lancamento.setId( dto.getId() );
        lancamento.setDescricao( dto.getDescricao() );
        lancamento.setAno( dto.getAno() );
        lancamento.setMes( dto.getMes() );
        lancamento.setValor( dto.getValor() );
        lancamento.setTipoLancamento( dto.getTipoLancamento() );
        lancamento.setStatusLancamento( dto.getStatusLancamento() );
        lancamento.setDataCadastro( dto.getDataCadastro() );

        return lancamento;
    }

    @Override
    public List<LancamentoDTO> toDto(List<Lancamento> entities) {
        if ( entities == null ) {
            return null;
        }

        List<LancamentoDTO> list = new ArrayList<LancamentoDTO>( entities.size() );
        for ( Lancamento lancamento : entities ) {
            list.add( toDto( lancamento ) );
        }

        return list;
    }

    @Override
    public List<Lancamento> toEntity(List<LancamentoDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Lancamento> list = new ArrayList<Lancamento>( dtos.size() );
        for ( LancamentoDTO lancamentoDTO : dtos ) {
            list.add( toEntity( lancamentoDTO ) );
        }

        return list;
    }
}
