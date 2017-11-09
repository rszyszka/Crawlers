/*
 * Copyright (C) 2017 Szysz
 */
package main;

import crud.HibernateUtil;
import crud.LoginHistoryCRUD;
import crud.RoleCRUD;
import crud.UsersCRUD;
import crud.UsersValidate;
import java.io.IOException;
import java.util.Date;
import others.LoginHistory;
import others.Roles;
import others.Users;

/**
 *
 * @author Szysz
 */
public class Main {

    public static void main(String[] args) throws IOException {

//        Users user = new Users("test12", "qweasd", 2);
//
//        if(UsersValidate.validateUser(user))
//            userCrud.addUser(user);
//        else
//            System.out.println("VALIDATION FALSE!");
        
        
//        LoginHistory log = new LoginHistory(1, new Date());
//        logCrud.addLog(log);
        
        
        for (Users el : UsersCRUD.getAllUsers()) {
            System.out.println("name: " + el.getName());
        }
        for (Roles el : RoleCRUD.getAllRoles()) {
            System.out.println("role name: " + el.getType());
        }
        for (LoginHistory el : LoginHistoryCRUD.getAllLogs()) {
            System.out.println("log date: " + el.getData());
        }

        HibernateUtil.getSessionFactory().close();
    }
}
