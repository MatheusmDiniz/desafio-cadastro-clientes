package com.desafio.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    private String usuario;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 5, max = 120, message = "O tamanho precisa ser de 5 a 120 caracter!")
    private String senha;
}
