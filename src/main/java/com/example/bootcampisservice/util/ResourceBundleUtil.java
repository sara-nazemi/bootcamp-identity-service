package com.example.bootcampisservice.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Component
public class ResourceBundleUtil {


    Map<String, String> locales = new HashMap<>() {{
        put("fa", "IR");
        put("en", "US");
    }};

    public String getMessage(String code, String language) {
        String country = locales.get(language);
        if (country == null) {
            country = "IR";
            language = "fa";
        }

        Locale locale = new Locale(language, country);
        ResourceBundle bundle = ResourceBundle.getBundle("exception", locale);

        String message = "";
        message = bundle.getString(code);

        return message;
    }

}
