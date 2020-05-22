package com.wolftech.wolfbills.mapper;


import com.wolftech.wolfbills.dto.UsuarioDTO;
import com.wolftech.wolfbills.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDTO> {

}
