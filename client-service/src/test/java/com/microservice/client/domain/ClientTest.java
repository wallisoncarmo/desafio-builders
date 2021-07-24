package com.microservice.client.domain;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ClientTest {

    public static final String CPF_SEM_MASCARA = "41757915079";
    public static final String CPF_COM_MASCARA = "417.579.150-79";

    @Test
    public void getCpfValid() {
        Client obj = new Client();
        obj.setCpf(CPF_SEM_MASCARA);
        Assertions.assertEquals(CPF_COM_MASCARA,obj.getCpf());
    }

}
