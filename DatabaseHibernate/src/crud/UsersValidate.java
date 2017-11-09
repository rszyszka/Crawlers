/*
 * Copyright (C) 2017 Szysz
 */
package crud;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import others.Users;

/**
 *
 * @author Szysz
 */
public class UsersValidate {

    public static boolean validateName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]{4,20}$");
        Matcher matcher = pattern.matcher(name);

        return matcher.find();
    }

    public static boolean validatePassword(String pass) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]{6,20}$");
        Matcher matcher = pattern.matcher(pass);

        return matcher.find();
    }
    
    public static boolean validateUser(Users user){
        return validateName(user.getName()) && validatePassword(user.getPassword());
    }
}
