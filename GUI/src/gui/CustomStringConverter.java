/*
 * Copyright (C) 2017 Szysz
 */
package gui;

import javafx.util.StringConverter;

/**
 *
 * @author Szysz
 */
public class CustomStringConverter extends StringConverter<Number> {

    public CustomStringConverter() {
  
    }

    @Override
    public String toString(Number number) {
        
        if(number.intValue()!=number.doubleValue())
            return "";
        return ""+number.intValue();
    }

    @Override
    public Number fromString(String string) {
        Number val = Double.parseDouble(string);
        return val.intValue();
    }


  
 
    
}
