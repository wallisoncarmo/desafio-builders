package com.microservice.client.utils;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class StringUtilsTest {

    public static final String CPF = "41757915079";
    public static final String FORMAT = "###.###.###-##";
    public static final String CPF_FORMATED = "417.579.150-79";

    @Test
    public void removeSpecialCaracterValid(){
        String text = StringUtils.removeSpecialCaracter(CPF_FORMATED);
        Assertions.assertEquals(CPF,text);
    }

    @Test
    public void removeSpecialCaracterTextNull(){
        String text = StringUtils.removeSpecialCaracter(null);
        Assertions.assertNull(text);
    }

    @Test
    public void addMaskFormatterValid(){
        String text = StringUtils.addMaskFormatter(CPF, FORMAT);
        Assertions.assertEquals(CPF_FORMATED,text);
    }

    @Test
    public void addMaskFormatterTextNull(){
        String text = StringUtils.addMaskFormatter(null,FORMAT);
        Assertions.assertNull(text);
    }

    @Test
    public void addMaskFormatterFormatNull(){
        String textFormated = StringUtils.addMaskFormatter(CPF,null);
        Assertions.assertEquals(CPF,textFormated);
    }
}
