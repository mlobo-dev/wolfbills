package com.wolftech.wolfbills.mapper;

import com.wolftech.wolfbills.dto.LancamentoDTO;
import com.wolftech.wolfbills.dto.UsuarioDTO;
import com.wolftech.wolfbills.model.Lancamento;
import com.wolftech.wolfbills.model.Usuario;
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
public class LancamentoMapperImpl implements LancamentoMapper {

    @Override
    public LancamentoDTO toDto(Lancamento entity) {
        if ( entity == null ) {
            return null;
        }

        LancamentoDTO lancamentoDTO = new LancamentoDTO();

        lancamentoDTO.setId( entity.getId() );
        lancamentoDTO.setDescricao( entity.getDescricao() );
        lancamentoDTO.setValor( entity.getValor() );
        lancamentoDTO.setTipo( entity.getTipo() );
        lancamentoDTO.setStatus( entity.getStatus() );
        lancamentoDTO.setDataCadastro( entity.getDataCadastro() );
        lancamentoDTO.setDataVencimento( entity.getDataVencimento() );
        lancamentoDTO.setUsuario( usuarioToUsuarioDTO( entity.getUsuario() ) );

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
        lancamento.setValor( dto.getValor() );
        lancamento.setTipo( dto.getTipo() );
        lancamento.setStatus( dto.getStatus() );
        lancamento.setDataCadastro( dto.getDataCadastro() );
        lancamento.setDataVencimento( dto.getDataVencimento() );
        lancamento.setUsuario( usuarioDTOToUsuario( dto.getUsuario() ) );

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
    public Set<Lancamento> toEntity(Set<LancamentoDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<Lancamento> set = new HashSet<Lancamento>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( LancamentoDTO lancamentoDTO : dtos ) {
            set.add( toEntity( lancamentoDTO ) );
        }

        return set;
    }

    protected UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( usuario.getId() );
        usuarioDTO.setNome( usuario.getNome() );
        usuarioDTO.setEmail( usuario.getEmail() );
        usuarioDTO.setSenha( usuario.getSenha() );

        return usuarioDTO;
    }

    protected Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( usuarioDTO.getId() );
        usuario.setNome( usuarioDTO.getNome() );
        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setSenha( usuarioDTO.getSenha() );

        return usuario;
    }
}
