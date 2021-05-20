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
public class EnderecoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "O campo cep é obrigatório")
    @Length(min = 9, max = 9, message = "O campo cep só suporta 9 caracteres")
    private String cep;

    @NotEmpty(message = "O campo logradouro é obrigatório")
    @Length(min = 3, max = 100, message = "O campo logradouro só suporta entre 3 e 100 caracteres")
    private String logradouro;

    @NotEmpty(message = "O campo bairro é obrigatório")
    @Length(min = 3, max = 100, message = "O campo bairro só suporta entre 3 e 100 caracteres")
    private String bairro;

    @NotEmpty(message = "O campo cidade é obrigatório")
    @Length(min = 3, max = 100, message = "O campo cidade só suporta entre 3 e 100 caracteres")
    private String cidade;

    @NotEmpty(message = "O campo uf é obrigatório")
    private String uf;

    @Length(min = 3, max = 200, message = "O campo complemento só suporta entre 3 e 100 caracteres")
    private String complemento;
}
