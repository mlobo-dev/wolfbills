package com.wolftech.wolfbills.mapper;


import com.wolftech.wolfbills.dto.LancamentoCadastroDTO;
import com.wolftech.wolfbills.model.Lancamento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LancamentoCadastroMapper extends BaseMapper<Lancamento, LancamentoCadastroDTO> {

}
