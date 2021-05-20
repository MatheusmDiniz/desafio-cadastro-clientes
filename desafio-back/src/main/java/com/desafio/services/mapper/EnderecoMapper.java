package com.desafio.services.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.desafio.domain.Endereco;
import com.desafio.services.dto.EnderecoDTO;

@Mapper(componentModel = "spring", uses = {})
public interface EnderecoMapper extends EntityMapper<EnderecoDTO, Endereco> {

}