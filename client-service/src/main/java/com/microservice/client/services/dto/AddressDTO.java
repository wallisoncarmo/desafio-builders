package com.microservice.client.services.dto;


import com.microservice.client.domain.enums.UF;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(max = 8, min = 8,message = "Campo cep só é permitido ter 8 caracter")
    @NotNull(message = "Campo de cep é obrigatório!")
    private String cep;

    @NotNull(message = "Campo de logradouro é obrigatório!")
    @Size(max = 100, min = 3,message = "Campo de logradouro só permitido ter 3 a 100 caracters")
    private String publicPlace;

    @NotNull(message = "Campo de bairro é obrigatório!")
    @Size(max = 100, min = 3,message = "Campo de bairro só permitido ter 3 a 100 caracters")
    private String neighborhood;

    @NotNull(message = "Campo de cidade é obrigatório!")
    @Size(max = 100, min = 3,message = "Campo de cidade só permitido ter 3 a 100 caracters")
    private String city;

    @NotNull(message = "Campo de UF é obrigatório!")
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Size(max = 254,message = "Campo de cidade só permitido ter 254 caracters")
    private String complement;

}
