package com.desafio.services.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.desafio.domain.Contato;
import com.desafio.domain.Endereco;
import com.desafio.services.dto.ContatoDTO;
import com.desafio.services.dto.EnderecoDTO;

@Mapper(componentModel = "spring", uses = {})
public interface ContatoMapper extends EntityMapper<ContatoDTO, Contato> {

}