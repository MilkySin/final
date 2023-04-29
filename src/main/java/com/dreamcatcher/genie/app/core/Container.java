package com.dreamcatcher.genie.app.core;


import java.util.HashMap;
import java.util.function.Supplier;


public class Container {
    protected HashMap<String, Supplier<?>> bindings = new HashMap<>();
    protected HashMap<String, Object> singletons = new HashMap<>();

    public Container() {
    }

    public void bind(String key, Supplier<?> resolver) {
        this.bindings.put(key, resolver);
    }

    public Object singleton(String key) throws Exception {
        Object instance = this.bindings.get(key);
        if (instance == null) {
            instance = this.resolve(key);
            this.singletons.put(key, instance);
        }
        return instance;
    }

    public Object resolve(String key) throws Exception {
        if (this.bindings.get(key) == null) {
            throw new Exception("No matching binding found for " + key);
        }

        var resolver = this.bindings.get(key);
        return resolver.get();
    }
}
