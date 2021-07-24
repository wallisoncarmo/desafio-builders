package com.microservice.client.services.dto;


import com.microservice.client.domain.enums.UF;
import com.microservice.client.utils.StringUtils;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String cpf;

    private String cep;

    private String publicPlace;

    private String neighborhood;

    private String city;

    private UF uf;

    private String complement;

    private String email;

    private Integer idade;

    public String getCpf() {
        return StringUtils.removeSpecialCaracter(cpf);
    }

    public String getCep() {
        return StringUtils.removeSpecialCaracter(cep);
    }


}
