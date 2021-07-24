package com.microservice.client.services.dto;


import com.microservice.client.domain.enums.UF;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClientDTOTest {

    @Test
    public void preencheContrutor() {
        ClientDTO objDTO =  new ClientDTO(null, "Sindicato dos Empregados no Comércio do DF", "34705270000110", getEndereco(), LocalDate.now(),29);
        Assertions.assertNotNull(objDTO);
    }

    private AddressDTO getEndereco() {
        return new AddressDTO(null, "72110-035", "Cna 3 - Lt 14 S 103", "Taguatinga", "Brasília", UF.DF, "");
    }

}
