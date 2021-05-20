package com.desafio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import com.desafio.domain.enums.TipoTelefone;

import javax.persistence.*;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.ParseException;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Campo numero é obrigatório")
    private String numero;

    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;

    @PrePersist
    @PreUpdate
    public void removerMascaraNumero() {
        this.numero = this.numero.replace("-", "");
    }

    public String getNumero() {
        try {
            MaskFormatter mf = new MaskFormatter("####-####");
            mf.setValueContainsLiteralCharacters(false);

            if (this.tipo.equals(TipoTelefone.CELULAR)) {
                mf.setMask("#####-####");
            }

            this.numero = mf.valueToString(this.numero);
        } catch (ParseException e) {
            e.getCause();
        }
        return this.numero;
    }
}
