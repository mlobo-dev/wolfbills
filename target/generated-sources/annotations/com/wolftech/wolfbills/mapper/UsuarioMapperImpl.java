package com.wolftech.wolfbills.mapper;

import com.wolftech.wolfbills.dto.UsuarioDTO;
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
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO toDto(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( entity.getId() );
        usuarioDTO.setNome( entity.getNome() );
        usuarioDTO.setEmail( entity.getEmail() );
        usuarioDTO.setSenha( entity.getSenha() );

        return usuarioDTO;
    }

    @Override
    public Usuario toEntity(UsuarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( dto.getId() );
        usuario.setNome( dto.getNome() );
        usuario.setEmail( dto.getEmail() );
        usuario.setSenha( dto.getSenha() );

        return usuario;
    }

    @Override
    public List<UsuarioDTO> toDto(List<Usuario> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>( entities.size() );
        for ( Usuario usuario : entities ) {
            list.add( toDto( usuario ) );
        }

        return list;
    }

    @Override
    public List<Usuario> toEntity(List<UsuarioDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Usuario> list = new ArrayList<Usuario>( dtos.size() );
        for ( UsuarioDTO usuarioDTO : dtos ) {
            list.add( toEntity( usuarioDTO ) );
        }

        return list;
    }

    @Override
    public Set<Usuario> toDto(Set<Usuario> entities) {
        if ( entities == null ) {
            return null;
        }

        Set<Usuario> set = new HashSet<Usuario>( Math.max( (int) ( entities.size() / .75f ) + 1, 16 ) );
        for ( Usuario usuario : entities ) {
            set.add( usuario );
        }

        return set;
    }

    @Override
    public Set<Usuario> toEntity(Set<UsuarioDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<Usuario> set = new HashSet<Usuario>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( UsuarioDTO usuarioDTO : dtos ) {
            set.add( toEntity( usuarioDTO ) );
        }

        return set;
    }
}
