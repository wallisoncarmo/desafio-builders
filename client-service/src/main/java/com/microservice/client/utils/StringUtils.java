package com.microservice.client.utils;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.Objects;

public class StringUtils {

    public static String removeSpecialCaracter(String text){
        if(Objects.isNull(text)){
            return text;
        }
        return text.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static String addMaskFormatter(String text, String format){
        if(Objects.isNull(text) || Objects.isNull(format) ){
            return text;
        }
        try {
            MaskFormatter mf = new MaskFormatter(format);
            mf.setValueContainsLiteralCharacters(false);
            text = mf.valueToString(text);
        } catch (ParseException e) {
            e.getCause();
        }
        return text;
    }

}
