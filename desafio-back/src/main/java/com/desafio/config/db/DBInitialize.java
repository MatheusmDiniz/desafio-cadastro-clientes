package com.desafio.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio.domain.Cliente;
import com.desafio.domain.Contato;
import com.desafio.domain.Endereco;
import com.desafio.domain.User;
import com.desafio.domain.enums.Profile;
import com.desafio.domain.enums.SiglaUnidadeFederativa;
import com.desafio.domain.enums.TipoTelefone;
import com.desafio.repositoties.ClienteRepository;
import com.desafio.repositoties.UserRepository;

import java.text.ParseException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DBInitialize {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bc;

    public void instantieateTestDatabase() throws ParseException {
        // BLOCO USUARIOS
        System.out.println("Adiciona usuarios");

        User admin = User.builder()
                .usuario("admin")
                .senha(bc.encode("123456"))
                .perfis(Arrays.asList(Profile.ADMIN.getCod()))
                .build();
        User cliente = User.builder()
                .usuario("comum")
                .senha(bc.encode("123456"))
                .perfis(Arrays.asList(Profile.USER.getCod()))
                .build();

        userRepository.saveAll(Arrays.asList(admin, cliente));

        System.out.println("Adiciona clientes");
        Cliente matheus = Cliente.builder()
                .nome("matheus")
                .cpf("06691298101")
                .endereco(Endereco.builder()
                        .cep("70390135")
                        .bairro("Asa Sul")
                        .cidade("Brasília")
                        .complemento("SEPS 713/913")
                        .logradouro("Bl. B, Aprt. 312")
                        .uf(SiglaUnidadeFederativa.DF)
                        .build())
                .contato(
                        Arrays.asList(
                                Contato.builder()
                                        .numero("(61)98414-0571")
                                        .tipo(TipoTelefone.CELULAR)
                                        .build(),
                                Contato.builder()
                                        .numero("(61)3000-0000")
                                        .tipo(TipoTelefone.RESIDENCIAL)
                                        .build()
                        )
                )
                .emails(Arrays.asList("matheus@gmail.com", "matheus@hotmail.com"))
                .build();
        clienteRepository.saveAll(Arrays.asList(matheus));
        System.out.println("=============================");
    }
}
