package com.microservice.client.builder;

import com.microservice.client.domain.Client;
import com.microservice.client.domain.Address;
import com.microservice.client.services.dto.AddressDTO;
import com.microservice.client.services.dto.ClientDTO;
import com.microservice.client.domain.enums.UF;
import com.microservice.client.services.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class ClientBuilder {

    public ClientDTO getClientDTO(){
        return  ClientDTO.builder()
                .id(1L)
                .cpf("027.019.900-41")
                .address(getAddressDTO())
                .name("Wallison do carmo costa")
                .nascimento(LocalDate.now().minusYears(20))
                .build();
    }

    public Client getClientEntity() {
        return Client.builder()
                .id(1L)
                .cpf("027.019.900-41")
                .address(getAddress())
                .name("Wallison do carmo costa")
                .nascimento(LocalDate.now().minusYears(20))
                .build();
    }

    private AddressDTO getAddressDTO() {
        return AddressDTO.builder()
                .cep("72110-035")
                .city("Brasília")
                .neighborhood("Taguatinga")
                .publicPlace("Catolica")
                .uf(UF.DF)
                .complement("CNA")
                .build();
    }

    private Address getAddress() {
        return Address.builder()
                .cep("72110-035")
                .city("Brasília")
                .neighborhood("Taguatinga")
                .publicPlace("Catolica")
                .uf(UF.DF)
                .complement("CNA")
                .build();
    }

}
