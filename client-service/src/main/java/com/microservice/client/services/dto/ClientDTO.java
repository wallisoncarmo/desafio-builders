package com.microservice.client.services.dto;


import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Campo de nome é obrigatório!")
    @Size(min = 5, max = 150,message = "O nome deve ter de no minimo 5 e no máximo 150 caracter")
    @Pattern(regexp = "^[áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑa-zA-Z0-9_ ]*")
    private String name;

    @CPF(message = "CPF é inválido")
    @NotNull(message = "Campo de CPF é obrigatório!")
    private String cpf;

    @NotNull(message = "Campo de CPF é obrigatório!")
    private AddressDTO address;

    @NotNull(message = "Campo de Data de Nascimento é obrigatório!")
    @Past(message = "Data de Nascimento não pode ser Futura!")
    private LocalDate nascimento;

    private Integer idade;

    public Integer getIdade() {
        if(Objects.isNull(nascimento)){
            return null;
        }
        return Period.between(nascimento, LocalDate.now()).getYears();
    }

}
