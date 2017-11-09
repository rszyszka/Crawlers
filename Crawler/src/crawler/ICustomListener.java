/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.util.EventListener;

/**
 *
 * @author rszyszka
 * @param <T>
 */
@FunctionalInterface
public interface ICustomListener<T> extends EventListener {
    
    void handle(Object source, T object);
    
}
