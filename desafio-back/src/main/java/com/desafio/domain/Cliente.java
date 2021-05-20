package com.desafio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 100)
    @NotNull(message = "Campo nome é obrigatório")
    private String nome;

    @CPF
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Campo endereco é obrigatório")
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<Contato> contato;

    @ElementCollection
    @CollectionTable(name = "emails")
    private List<String> emails;

    @PrePersist
    @PreUpdate
    public void removerMascaraCpf() {
        this.cpf = this.cpf.replace(".", "").replace("-", "");
    }

    public String getCpf() {

        try {
            MaskFormatter mf = new MaskFormatter("###.###.###-##");
            mf.setValueContainsLiteralCharacters(false);
            this.cpf = mf.valueToString(this.cpf);
        } catch (ParseException e) {
            e.getCause();
        }
        return this.cpf;
    }

}
