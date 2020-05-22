package com.wolftech.wolfbills.mapper;

import com.wolftech.wolfbills.dto.LancamentoCadastroDTO;
import com.wolftech.wolfbills.model.Lancamento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-22T13:34:59-0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class LancamentoCadastroMapperImpl implements LancamentoCadastroMapper {

    @Override
    public LancamentoCadastroDTO toDto(Lancamento entity) {
        if ( entity == null ) {
            return null;
        }

        LancamentoCadastroDTO lancamentoCadastroDTO = new LancamentoCadastroDTO();

        lancamentoCadastroDTO.setId( entity.getId() );
        lancamentoCadastroDTO.setDescricao( entity.getDescricao() );
        lancamentoCadastroDTO.setAno( entity.getAno() );
        lancamentoCadastroDTO.setMes( entity.getMes() );
        lancamentoCadastroDTO.setValor( entity.getValor() );
        lancamentoCadastroDTO.setTipoLancamento( entity.getTipoLancamento() );
        lancamentoCadastroDTO.setStatusLancamento( entity.getStatusLancamento() );
        lancamentoCadastroDTO.setDataCadastro( entity.getDataCadastro() );

        return lancamentoCadastroDTO;
    }

    @Override
    public Lancamento toEntity(LancamentoCadastroDTO dto) {
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
    public List<LancamentoCadastroDTO> toDto(List<Lancamento> entities) {
        if ( entities == null ) {
            return null;
        }

        List<LancamentoCadastroDTO> list = new ArrayList<LancamentoCadastroDTO>( entities.size() );
        for ( Lancamento lancamento : entities ) {
            list.add( toDto( lancamento ) );
        }

        return list;
    }

    @Override
    public List<Lancamento> toEntity(List<LancamentoCadastroDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Lancamento> list = new ArrayList<Lancamento>( dtos.size() );
        for ( LancamentoCadastroDTO lancamentoCadastroDTO : dtos ) {
            list.add( toEntity( lancamentoCadastroDTO ) );
        }

        return list;
    }
}
