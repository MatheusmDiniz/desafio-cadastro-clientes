package com.desafio.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import com.desafio.domain.enums.TipoTelefone;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Campo de numero não pode ser vazio")
    @Length(min = 10, max = 15, message = "Campo de numero suporta no minimo 14 entre 15 caracter")
    private String numero;

    @NotNull(message = "Campo de tipo não pode ser vazio")
    private TipoTelefone tipo;
}
