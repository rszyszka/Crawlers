/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

/**
 *
 * @author Szysz
 */
public class CrawlerException extends Exception {
    
    CrawlerException(){
        System.err.println("Blednie skonfigurowany adres");
    }
    
}
