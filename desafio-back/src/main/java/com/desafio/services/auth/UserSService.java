package com.desafio.services.auth;

import org.springframework.security.core.context.SecurityContextHolder;

import com.desafio.security.UserSS;

public class UserSService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
