package com.microservice.client.builder;

import com.microservice.client.domain.enums.UF;
import com.microservice.client.services.dto.FilterClientDTO;
import org.springframework.stereotype.Component;

@Component
public class FilterClientBuilder {

    public FilterClientDTO getFilterClientDTO() {
        return FilterClientDTO.builder()
                .name("Wallison")
                .cpf("027.019.900-41")
                .cep("72.120-600")
                .publicPlace("Cna 3 - Lt 14 S 103")
                .neighborhood("Taguatinga")
                .city("Brasília")
                .uf(UF.DF)
                .email("wallisoncarmo01@gmail")
                .idade(30)
                .complement("Bloco E")
                .build();
    }
}
