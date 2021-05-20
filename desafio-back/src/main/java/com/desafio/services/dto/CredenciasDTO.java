package com.desafio.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class CredenciasDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Campo usuário é obrigatório!")
	private String usuario;

	@NotEmpty(message = "Campo senha é obrigatório!")
	@Length(min = 5, max = 120, message = "O tamanho precisa ser de 5 a 120 caracter!")
	private String senha;
}
