package com.desafio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.desafio.domain.enums.SiglaUnidadeFederativa;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 8)
    @NotNull(message = "Campo cep é obrigatório")
    private String cep;

    @Size(max = 100)
    @NotNull(message = "Campo logradouro é obrigatório")
    private String logradouro;

    @Size(max = 50)
    @NotNull(message = "Campo bairro é obrigatório")
    private String bairro;

    @Size(max = 50)
    @NotNull(message = "Campo cidade é obrigatório")
    private String cidade;

    @Size(max = 200)
    private String complemento;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Campo nome é obrigatório")
    private SiglaUnidadeFederativa uf;

    @PrePersist
    @PreUpdate
    public void removerMascaraCep() {

        this.cep = Objects.nonNull(this.cep) ? this.cep.replace(".", "").replace("-", "") : null;
    }

    public String getCep() {
        try {
            MaskFormatter mf = new MaskFormatter("#####-###");
            mf.setValueContainsLiteralCharacters(false);
            this.cep = mf.valueToString(this.cep);
        } catch (ParseException e) {
            e.getCause();
        }
        return this.cep;
    }

}
