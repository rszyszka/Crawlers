/*
 * Copyright (C) 2017 Szysz
 */
package crawler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Szysz
 * @param <T>
 */
public class CustomEvent<T> {

    private final List<ICustomListener<T>> listeners = new ArrayList<>();

    public void fire(Object source, T object) {
        for (ICustomListener<T> el : this.listeners) {
            el.handle(source, object);
        }
    }

    public void add(ICustomListener<T> listener) {
        this.listeners.add(listener);
    }

    public void remove(ICustomListener<T> listener) {
        this.listeners.remove(listener);
    }
}
