package com.desafio.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "O campo nome é obrigatório")
    @Length(min = 3, max = 100, message = "O nome precisa ter de 3 a 100 caracteres!")
    @Pattern(regexp = "^[áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑa-zA-Z0-9_ ]*", message = "Formato do campo nome é inválido")
    private String nome;

    @NotEmpty(message = "O campo cpf é obrigatório!")
    @CPF
    private String cpf;

    @NotNull(message = "O campo endereco é obrigatório!")
    @Valid
    private EnderecoDTO endereco;

    @NotEmpty(message = "O campo email é obrigatório!")
    private List<String> emails;

    @NotEmpty(message = "O campo contato é obrigatório!")
    @Valid
    private List<ContatoDTO> contato;
    
}
