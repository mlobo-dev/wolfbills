package com.wolftech.wolfbills.mapper;

import com.wolftech.wolfbills.dto.LancamentoCadastroDTO;
import com.wolftech.wolfbills.model.Lancamento;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-03T18:52:14-0300",
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
        lancamentoCadastroDTO.setValor( entity.getValor() );
        lancamentoCadastroDTO.setTipo( entity.getTipo() );
        lancamentoCadastroDTO.setStatus( entity.getStatus() );
        lancamentoCadastroDTO.setDataCadastro( entity.getDataCadastro() );
        lancamentoCadastroDTO.setDataVencimento( entity.getDataVencimento() );

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
        lancamento.setValor( dto.getValor() );
        lancamento.setTipo( dto.getTipo() );
        lancamento.setStatus( dto.getStatus() );
        lancamento.setDataCadastro( dto.getDataCadastro() );
        lancamento.setDataVencimento( dto.getDataVencimento() );

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

    @Override
    public Set<Lancamento> toDto(Set<Lancamento> entities) {
        if ( entities == null ) {
            return null;
        }

        Set<Lancamento> set = new HashSet<Lancamento>( Math.max( (int) ( entities.size() / .75f ) + 1, 16 ) );
        for ( Lancamento lancamento : entities ) {
            set.add( lancamento );
        }

        return set;
    }

    @Override
    public Set<Lancamento> toEntity(Set<LancamentoCadastroDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<Lancamento> set = new HashSet<Lancamento>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( LancamentoCadastroDTO lancamentoCadastroDTO : dtos ) {
            set.add( toEntity( lancamentoCadastroDTO ) );
        }

        return set;
    }
}
