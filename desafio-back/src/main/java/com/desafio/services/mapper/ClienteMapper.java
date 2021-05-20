package com.desafio.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.desafio.domain.Cliente;
import com.desafio.services.dto.ClienteDTO;

@Mapper(componentModel = "spring", uses = {})
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente> {
}