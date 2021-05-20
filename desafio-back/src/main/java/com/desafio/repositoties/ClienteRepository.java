package com.desafio.repositoties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.domain.Cliente;
import com.desafio.domain.User;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
