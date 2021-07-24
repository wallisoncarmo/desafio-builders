package com.microservice.client.services.dto;


import com.microservice.client.builder.FilterClientBuilder;
import com.microservice.client.domain.enums.UF;
import com.microservice.client.services.ClientService;
import com.microservice.client.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FilterClientDTOTest {


    private FilterClientBuilder filterClientBuilder;

    @Before
    public void setUp(){
        filterClientBuilder = new FilterClientBuilder();
    }

    @Test
    public void preencheContrutor() {
        FilterClientDTO objDTO =  new FilterClientDTO("Wallison","027.019.900-41","72.120-600","Cna 3 - Lt 14 S 103","Taguatinga","Brasília",UF.DF,"","wallisoncarmo01@gmail",30);
        Assertions.assertNotNull(objDTO);
    }

    @Test
    public void whenGetCpfThenReturn() {
        FilterClientDTO filterClientDTO = filterClientBuilder.getFilterClientDTO();
        Assertions.assertEquals(filterClientDTO.getCpf(),"02701990041");
    }

    @Test
    public void whenGetCepThenReturn() {
        FilterClientDTO filterClientDTO = filterClientBuilder.getFilterClientDTO();
        Assertions.assertEquals(filterClientDTO.getCep(),"72120600");
    }

}
