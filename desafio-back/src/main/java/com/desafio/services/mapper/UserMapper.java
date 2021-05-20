package com.desafio.services.mapper;

import org.mapstruct.Mapper;

import com.desafio.domain.User;
import com.desafio.services.dto.UserDTO;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User> {
	
}